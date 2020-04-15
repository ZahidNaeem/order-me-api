package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.exception.BadRequestException;
import com.alfauz.orderme.repo.UserRepo;
import com.alfauz.orderme.service.UserService;
import com.alfauz.orderme.utils.Miscellaneous;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

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
    public Boolean existsByUsername(final String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(final String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        Miscellaneous.constraintViolation(userEntity);
        try {
            return userRepo.saveAndFlush(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }
}
