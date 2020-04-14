package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.Role;
import com.alfauz.orderme.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

  Optional<Role> findByName(RoleName roleName);
}
