package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.SubCategoryEntity;
import com.alfauz.orderme.exception.BadRequestException;
import com.alfauz.orderme.repo.SubCategoryRepo;
import com.alfauz.orderme.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepo subCategoryRepo;

    @Override
    public SubCategoryEntity findById(Long id) {
        return subCategoryRepo.findById(id)
                .orElse(null);
    }

    @Override
    public SubCategoryEntity save(SubCategoryEntity entity) {
        try {
            return subCategoryRepo.saveAndFlush(entity);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
