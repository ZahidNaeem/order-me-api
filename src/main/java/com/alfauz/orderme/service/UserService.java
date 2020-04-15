package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity findById(final Long id);

    UserEntity findByUsername(final String username);

    UserEntity findByEmail(final String email);

    UserEntity findByUsernameOrEmail(final String username, final String email);

    List<UserEntity> findByIdIn(final List<Long> userIds);

    Boolean existsByUsername(final String username);

    Boolean existsByEmail(final String email);

    UserEntity save(final UserEntity userEntity);
}
