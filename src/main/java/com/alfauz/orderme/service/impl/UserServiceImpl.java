package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.CountryCodeEntity;
import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.entity.UserMainCategoryEntity;
import com.alfauz.orderme.exception.BadRequestException;
import com.alfauz.orderme.repo.RoleRepo;
import com.alfauz.orderme.repo.UserMainCategoryRepo;
import com.alfauz.orderme.repo.UserRepo;
import com.alfauz.orderme.service.CountryCodeService;
import com.alfauz.orderme.service.UserService;
import com.alfauz.orderme.utils.Miscellaneous;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final CountryCodeService countryCodeService;
    private final UserMainCategoryRepo userMainCategoryRepo;
    private final RoleRepo roleRepo;

    @Override
    public List<UserEntity> findAll() {
        return userRepo.findAllByOrderByUserTypeAscUsernameAsc();
    }

    @Override
    public UserEntity findById(final Long id) {
        return userRepo.findById(id)
                .orElse(null);
    }

    @Override
    public UserEntity findByUsername(final String username) {
        return userRepo.findByUsername(username)
                .orElse(null);
    }

    @Override
    public UserEntity findByEmail(final String email) {
        return userRepo.findByEmail(email)
                .orElse(null);
    }

    @Override
    public UserEntity findByUsernameOrEmail(final String username, String email) {
        return userRepo.findByUsernameOrEmail(username, email)
                .orElse(null);
    }

    @Override
    public List<UserEntity> findByIdIn(final List<Long> userIds) {
        return userRepo.findByIdIn(userIds);
    }

    @Override
    public Boolean existsByCountryCodeAndPhone(final Long countryCode, final String phone) {
        final CountryCodeEntity countryCodeEntity = countryCodeService.findById(countryCode);
        return userRepo.existsByCountryCodeAndPhone(countryCodeEntity, phone);
    }

    @Override
    public Boolean existsByEmail(final String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(final String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        Miscellaneous.constraintViolation(userEntity);
        final List<UserMainCategoryEntity> userMainCategories = userEntity.getUserMainCategories();
        if (CollectionUtils.isNotEmpty(userMainCategories)) {
            userMainCategories.forEach(cat -> cat.setUser(userEntity));
        }
        try {
            final Long id = userEntity.getId();
            if (id != null) {
                final UserEntity entity = userRepo.findById(id).orElse(null);
                if (entity != null) {
                    userMainCategoryRepo.deleteInBatch(entity.getUserMainCategories());
                    roleRepo.deleteInBatch(entity.getRoles());
                }
            }
            return userRepo.saveAndFlush(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }
}
