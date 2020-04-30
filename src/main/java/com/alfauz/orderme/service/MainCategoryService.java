package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.MainCategoryEntity;

import java.util.List;

public interface MainCategoryService {

    List<MainCategoryEntity> findAll();

    MainCategoryEntity findById(final Long id);

    MainCategoryEntity save(final MainCategoryEntity entity);
}
