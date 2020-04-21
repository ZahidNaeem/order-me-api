package com.alfauz.orderme.mapper;

import com.alfauz.orderme.entity.UserSaleItemCategoryEntity;
import com.alfauz.orderme.mapper.qualifier.UserSaleItemCategoryQualifier;
import com.alfauz.orderme.model.UserSaleItemCategoryModel;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {UserSaleItemCategoryQualifier.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserSaleItemCategoryMapper {

    @Mapping(target = "itemCategory", qualifiedByName = "itemCategoryModelToEntity")
    @Mapping(target = "user", qualifiedByName = "userModelToEntity")
    UserSaleItemCategoryEntity toEntity(final UserSaleItemCategoryModel model);

    @Mapping(target = "itemCategory", qualifiedByName = "itemCategoryEntityToModel")
    @Mapping(target = "user", qualifiedByName = "userEntityToModel")
    UserSaleItemCategoryModel toModel(final UserSaleItemCategoryEntity userSaleItemCategory);

    default List<UserSaleItemCategoryEntity> toEntities(final List<UserSaleItemCategoryModel> models) {
        final List<UserSaleItemCategoryEntity> userSaleItemCategorys = new ArrayList<>();
        if (CollectionUtils.isEmpty(models)) {
            return userSaleItemCategorys;
        }
        models.forEach(model -> userSaleItemCategorys.add(toEntity(model)));
        return userSaleItemCategorys;
    }

    default List<UserSaleItemCategoryModel> toModels(final List<UserSaleItemCategoryEntity> entities) {
        final List<UserSaleItemCategoryModel> models = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return models;
        }
        entities.forEach(entity -> models.add(toModel(entity)));
        return models;
    }
}
