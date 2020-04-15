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

    @Mapping(target = "roles", qualifiedByName = "rolesModelsToEntities")
    @Mapping(target = "userAddresses", qualifiedByName = "userAddressesModelsToEntities")
    UserEntity toEntity(final UserModel model);

    @Mapping(target = "roles", qualifiedByName = "rolesEntitiesToModels")
    @Mapping(target = "userAddresses", qualifiedByName = "userAddressesEntitiesToModels")
    UserModel toModel(final UserEntity user);

    default List<UserEntity> toEntities(final List<UserModel> models) {
        final List<UserEntity> users = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return users;
        }
        models.forEach(model -> {
            users.add(toEntity(model));
        });
        return users;
    }

    default List<UserModel> toModels(final List<UserEntity> Users) {
        final List<UserModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(Users)) {
            return models;
        }
        Users.forEach(User -> {
            models.add(toModel(User));
        });
        return models;
    }
}
