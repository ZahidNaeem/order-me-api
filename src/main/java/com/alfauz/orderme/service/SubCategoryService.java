package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.SubCategoryEntity;
import com.alfauz.orderme.model.SubCategoryModel;

import java.util.List;

public interface SubCategoryService {

    List<SubCategoryEntity> findAll();

    List<SubCategoryModel> findAllByMainCategory(final Long id);

    SubCategoryEntity findById(final Long id);

    SubCategoryEntity save(final SubCategoryEntity entity);
}
