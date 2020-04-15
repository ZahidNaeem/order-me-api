package com.alfauz.orderme.service.impl;

import com.alfauz.orderme.entity.RoleEntity;
import com.alfauz.orderme.enumeration.RoleName;
import com.alfauz.orderme.repo.RoleRepo;
import com.alfauz.orderme.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public RoleEntity findById(final Long id) {
        return roleRepo.findById(id)
                .orElse(null);
    }

    @Override
    public RoleEntity findByName(final RoleName roleName) {
        return roleRepo.findByName(roleName)
                .orElse(null);
    }
}
