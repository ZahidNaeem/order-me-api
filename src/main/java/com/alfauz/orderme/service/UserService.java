package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();

    UserEntity findById(final Long id);

    UserEntity findByUsername(final String username);

    UserEntity findByEmail(final String email);

    UserEntity findByUsernameOrEmail(final String username, final String email);

    List<UserEntity> findByIdIn(final List<Long> userIds);

    Boolean existsByCountryCodeAndPhone(final Long countryCode, final String phone);

    Boolean existsByEmail(final String email);

    Boolean existsByUsername(final String username);

    UserEntity save(final UserEntity userEntity);
}
