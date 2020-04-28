package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.MainCategoryEntity;

public interface MainCategoryService {

    MainCategoryEntity findById(final Long id);

    MainCategoryEntity save(final MainCategoryEntity entity);
}
