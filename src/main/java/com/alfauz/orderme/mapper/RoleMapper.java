package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.RoleEntity;
import com.alfauz.orderme.mapper.qualifier.RoleQualifier;
import com.alfauz.orderme.model.RoleModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring",
        uses = {RoleQualifier.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper {

    RoleEntity toEntity(final RoleModel roleModel);

    RoleModel toModel(final RoleEntity roleEntity);

    default Set<RoleEntity> toEntities(final Set<RoleModel> models) {
        final Set<RoleEntity> entities = new HashSet<>();
        if (CollectionUtils.isEmpty(models)) {
            return entities;
        }
        models.forEach(model -> entities.add(toEntity(model)));
        return entities;
    }

    default Set<RoleModel> toModels(final Set<RoleEntity> entities) {
        final Set<RoleModel> models = new HashSet<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
