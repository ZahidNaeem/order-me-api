package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.UserMainCategoryEntity;

public interface UserMainCategoryService {

    UserMainCategoryEntity findById(final Long id);

    UserMainCategoryEntity save(final UserMainCategoryEntity entity);
}
