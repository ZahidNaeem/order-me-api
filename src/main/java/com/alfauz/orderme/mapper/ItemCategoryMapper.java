package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.ItemCategoryEntity;
import com.alfauz.orderme.mapper.qualifier.ItemCategoryQualifier;
import com.alfauz.orderme.model.ItemCategoryModel;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ItemCategoryQualifier.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ItemCategoryMapper {

    @Mapping(target = "userSaleItemCategories", qualifiedByName = "userSaleItemCategoriesModelsToEntities")
    ItemCategoryEntity toEntity(final ItemCategoryModel model);

    @Mapping(target = "userSaleItemCategories", qualifiedByName = "userSaleItemCategoriesEntitiesToModels")
    ItemCategoryModel toModel(final ItemCategoryEntity itemCategory);

    default List<ItemCategoryEntity> toEntities(final List<ItemCategoryModel> models) {
        final List<ItemCategoryEntity> itemCategories = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return itemCategories;
        }
        models.forEach(model -> itemCategories.add(toEntity(model)));
        return itemCategories;
    }

    default List<ItemCategoryModel> toModels(final List<ItemCategoryEntity> entities) {
        final List<ItemCategoryModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
