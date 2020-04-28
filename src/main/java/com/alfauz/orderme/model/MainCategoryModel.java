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
public class MainCategoryModel {
    private Long id;
    private String mainCatName;
    private List<UserMainCategoryModel> userMainCategories;
    private List<SubCategoryModel> subCategories;
}
