package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.UserAddressEntity;

import java.util.List;

public interface UserAddressService {

    List<UserAddressEntity> findAll();

    UserAddressEntity findById(final Long id);

    UserAddressEntity save(final UserAddressEntity userAddressEntity);

    void delete(final UserAddressEntity userAddressEntity);

    void deleteById(final Long id);

    void deleteInBatch(final List<UserAddressEntity> entities);
}
