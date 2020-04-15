package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.RoleEntity;
import com.alfauz.orderme.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {

  Optional<RoleEntity> findByName(final RoleName roleName);
}
