package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.ItemCategoryEntity;
import com.alfauz.orderme.repo.ItemCategoryRepo;
import com.alfauz.orderme.service.ItemCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepo itemCategoryRepo;

    @Override
    public ItemCategoryEntity findById(final String id) {
        return itemCategoryRepo.findById(id)
                .orElse(null);
    }
}
