package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.SubCategoryEntity;
import com.alfauz.orderme.mapper.qualifier.SubCategoryQualifier;
import com.alfauz.orderme.model.SubCategoryModel;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = SubCategoryQualifier.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SubCategoryMapper {

    @Mapping(target = "mainCategory", qualifiedByName = "mainCategoryMTE")
    SubCategoryEntity toEntity(final SubCategoryModel model);

    @Mapping(target = "mainCategory", qualifiedByName = "mainCategoryETM")
    SubCategoryModel toModel(final SubCategoryEntity entity);

    default List<SubCategoryEntity> toEntities(final List<SubCategoryModel> models) {
        final List<SubCategoryEntity> entities = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return entities;
        }
        models.forEach(model -> entities.add(toEntity(model)));
        return entities;
    }

    default List<SubCategoryModel> toModels(final List<SubCategoryEntity> entities) {
        final List<SubCategoryModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
