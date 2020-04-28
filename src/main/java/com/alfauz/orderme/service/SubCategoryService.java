package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.SubCategoryEntity;

public interface SubCategoryService {

    SubCategoryEntity findById(final Long id);

    SubCategoryEntity save(final SubCategoryEntity entity);
}
