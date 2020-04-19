package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByOrderByUserTypeAscUsernameAsc();

    Optional<UserEntity> findByUsername(final String username);

    Optional<UserEntity> findByEmail(final String email);

    Optional<UserEntity> findByUsernameOrEmail(final String username, final String email);

    List<UserEntity> findByIdIn(final List<Long> userIds);

    Boolean existsByUsername(final String username);

    Boolean existsByEmail(final String email);
}
