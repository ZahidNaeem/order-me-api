package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.MainCategoryEntity;
import com.alfauz.orderme.mapper.qualifier.MainCategoryQualifier;
import com.alfauz.orderme.model.MainCategoryModel;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = MainCategoryQualifier.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MainCategoryMapper {

    @Mapping(target = "subCategories", qualifiedByName = "subCategoriesMTE")
    @Mapping(target = "userMainCategories", qualifiedByName = "userMainCategoriesMTE")
    MainCategoryEntity toEntity(final MainCategoryModel model);

    @Mapping(target = "subCategories", qualifiedByName = "subCategoriesETM")
    @Mapping(target = "userMainCategories", qualifiedByName = "userMainCategoriesETM")
    MainCategoryModel toModel(final MainCategoryEntity entity);

    default List<MainCategoryEntity> toEntities(final List<MainCategoryModel> models) {
        final List<MainCategoryEntity> entities = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return entities;
        }
        models.forEach(model -> entities.add(toEntity(model)));
        return entities;
    }

    default List<MainCategoryModel> toModels(final List<MainCategoryEntity> entities) {
        final List<MainCategoryModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
