package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.UserMainCategoryEntity;
import com.alfauz.orderme.exception.BadRequestException;
import com.alfauz.orderme.repo.UserMainCategoryRepo;
import com.alfauz.orderme.service.UserMainCategoryService;
import com.alfauz.orderme.utils.Miscellaneous;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserMainCategoryServiceImpl implements UserMainCategoryService {

    private final UserMainCategoryRepo userMainCategoryRepo;

    @Override
    public UserMainCategoryEntity findById(Long id) {
        return userMainCategoryRepo.findById(id)
                .orElse(null);
    }

    @Override
    public UserMainCategoryEntity save(UserMainCategoryEntity entity) {
        try {
            return userMainCategoryRepo.saveAndFlush(entity);
        } catch (Exception e) {
            throw new BadRequestException(Miscellaneous.getNestedException(e).getMessage());
        }
    }
}
