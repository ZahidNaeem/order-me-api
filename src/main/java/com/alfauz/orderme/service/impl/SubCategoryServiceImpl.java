package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.MainCategoryEntity;
import com.alfauz.orderme.entity.SubCategoryEntity;
import com.alfauz.orderme.exception.BadRequestException;
import com.alfauz.orderme.mapper.SubCategoryMapper;
import com.alfauz.orderme.model.SubCategoryModel;
import com.alfauz.orderme.repo.MainCategoryRepo;
import com.alfauz.orderme.repo.SubCategoryRepo;
import com.alfauz.orderme.service.SubCategoryService;
import com.alfauz.orderme.utils.Miscellaneous;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepo subCategoryRepo;
    private final MainCategoryRepo mainCategoryRepo;
    private final SubCategoryMapper mapper;

    @Override
    public List<SubCategoryEntity> findAll() {
        return subCategoryRepo.findAll();
    }

    @Override
    public List<SubCategoryModel> findAllByMainCategory(final Long id) {
        final MainCategoryEntity mainCategory = mainCategoryRepo.findById(id)
                .orElseThrow(() -> new BadRequestException("Wrong ID provided"));
        final List<SubCategoryEntity> mainCategories = subCategoryRepo.findAllByMainCategory(mainCategory);
        return mapper.toModels(mainCategories);
    }

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
            throw new BadRequestException(Miscellaneous.getNestedException(e).getMessage());
        }
    }
}
