package com.alfauz.orderme.service;

import com.alfauz.orderme.entity.RoleEntity;
import com.alfauz.orderme.enumeration.RoleName;

public interface RoleService {

    RoleEntity findById(final Long id);

    RoleEntity findByName(final RoleName roleName);
}
