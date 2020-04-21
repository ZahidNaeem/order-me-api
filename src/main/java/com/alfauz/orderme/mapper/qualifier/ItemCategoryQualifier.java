package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.entity.UserSaleItemCategoryEntity;
import com.alfauz.orderme.mapper.UserSaleItemCategoryMapper;
import com.alfauz.orderme.model.UserSaleItemCategoryModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemCategoryQualifier {

    private final UserSaleItemCategoryMapper userSaleItemCategoryMapper;

    @Named(value = "userSaleItemCategoriesModelsToEntities")
    public List<UserSaleItemCategoryEntity> userSaleItemCategoriesMTE(final List<UserSaleItemCategoryModel> models) {
        return userSaleItemCategoryMapper.toEntities(models);
    }

    @Named(value = "userSaleItemCategoriesEntitiesToModels")
    public List<UserSaleItemCategoryModel> userSaleItemCategoriesETM(final List<UserSaleItemCategoryEntity> entities) {
        return userSaleItemCategoryMapper.toModels(entities);
    }
}
