package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.UserAddressEntity;
import com.alfauz.orderme.mapper.qualifier.UserAddressQualifier;
import com.alfauz.orderme.model.UserAddressModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = UserAddressQualifier.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserAddressMapper {

    @Mapping(target = "addressType", qualifiedByName = "addressTypeModelToEntity")
    @Mapping(target = "user", qualifiedByName = "userModelToEntity")
    UserAddressEntity toEntity(final UserAddressModel userAddressModel);

    @Mapping(target = "addressType", qualifiedByName = "addressTypeEntityToModel")
    @Mapping(target = "user", qualifiedByName = "userEntityToModel")
    UserAddressModel toModel(final UserAddressEntity userAddressEntity);

    default List<UserAddressEntity> toEntities(final List<UserAddressModel> models) {
        final List<UserAddressEntity> entities = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return entities;
        }
        models.forEach(model -> entities.add(toEntity(model)));
        return entities;
    }

    default List<UserAddressModel> toModels(final List<UserAddressEntity> entities) {
        final List<UserAddressModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
