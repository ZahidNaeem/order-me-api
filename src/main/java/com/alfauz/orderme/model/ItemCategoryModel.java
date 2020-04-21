package com.alfauz.orderme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryModel {
    private String itemCategory;
    private String mainCategory;
    private List<UserSaleItemCategoryModel> userSaleItemCategories;
}
