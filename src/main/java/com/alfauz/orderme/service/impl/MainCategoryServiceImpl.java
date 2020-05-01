package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.MainCategoryEntity;
import com.alfauz.orderme.exception.BadRequestException;
import com.alfauz.orderme.repo.MainCategoryRepo;
import com.alfauz.orderme.service.MainCategoryService;
import com.alfauz.orderme.utils.Miscellaneous;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MainCategoryServiceImpl implements MainCategoryService {

    private final MainCategoryRepo mainCategoryRepo;

    @Override
    public List<MainCategoryEntity> findAll() {
        return mainCategoryRepo.findAll();
    }

    @Override
    public MainCategoryEntity findById(Long id) {
        return mainCategoryRepo.findById(id)
                .orElse(null);
    }

    @Override
    public MainCategoryEntity save(MainCategoryEntity entity) {
        try {
            return mainCategoryRepo.saveAndFlush(entity);
        } catch (Exception e) {
            throw new BadRequestException(Miscellaneous.getNestedException(e).getMessage());
        }
    }
}
