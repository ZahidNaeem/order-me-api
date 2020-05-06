package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.UserMainCategoryEntity;
import com.alfauz.orderme.mapper.qualifier.UserMainCategoryQualifier;
import com.alfauz.orderme.model.UserMainCategoryModel;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = UserMainCategoryQualifier.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMainCategoryMapper {

    UserMainCategoryEntity toEntity(final UserMainCategoryModel model);

    UserMainCategoryModel toModel(final UserMainCategoryEntity entity);

    default List<UserMainCategoryEntity> toEntities(final List<UserMainCategoryModel> models) {
        final List<UserMainCategoryEntity> entities = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return entities;
        }
        models.forEach(model -> entities.add(toEntity(model)));
        return entities;
    }

    default List<UserMainCategoryModel> toModels(final List<UserMainCategoryEntity> entities) {
        final List<UserMainCategoryModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
