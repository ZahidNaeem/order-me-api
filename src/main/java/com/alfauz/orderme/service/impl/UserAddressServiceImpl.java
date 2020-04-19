package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.UserAddressEntity;
import com.alfauz.orderme.repo.UserAddressRepo;
import com.alfauz.orderme.service.UserAddressService;
import com.alfauz.orderme.utils.Miscellaneous;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepo userAddressRepo;

    @Override
    public List<UserAddressEntity> findAll() {
        return userAddressRepo.findAll();
    }

    @Override
    public UserAddressEntity findById(Long id) {
        return userAddressRepo.findById(id)
                .orElse(null);
    }

    @Override
    public UserAddressEntity save(UserAddressEntity userAddressEntity) {
        Miscellaneous.constraintViolation(userAddressEntity);
        try {
            return userAddressRepo.saveAndFlush(userAddressEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(UserAddressEntity userAddressEntity) {
        userAddressRepo.delete(userAddressEntity);
    }

    @Override
    public void deleteById(Long id) {
        userAddressRepo.deleteById(id);
    }

    @Override
    public void deleteInBatch(List<UserAddressEntity> entities) {
        userAddressRepo.deleteInBatch(entities);
    }
}
