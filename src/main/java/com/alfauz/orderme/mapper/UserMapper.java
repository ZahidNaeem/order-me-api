package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.mapper.qualifier.UserQualifier;
import com.alfauz.orderme.model.UserModel;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {UserQualifier.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {

    @Mapping(target = "countryCode", qualifiedByName = "countryCodeMTE")
    @Mapping(target = "userMainCategories", qualifiedByName = "userMainCategoriesMTE")
    @Mapping(target = "roles", qualifiedByName = "rolesMTE")
    UserEntity toEntity(final UserModel model);

    @Mapping(target = "countryCode", qualifiedByName = "countryCodeETM")
    @Mapping(target = "userMainCategories", qualifiedByName = "userMainCategoriesETM")
    @Mapping(target = "roles", qualifiedByName = "rolesETM")
    UserModel toModel(final UserEntity user);

    default List<UserEntity> toEntities(final List<UserModel> models) {
        final List<UserEntity> users = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return users;
        }
        models.forEach(model -> users.add(toEntity(model)));
        return users;
    }

    default List<UserModel> toModels(final List<UserEntity> entities) {
        final List<UserModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
