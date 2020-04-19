package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepo extends JpaRepository<UserAddressEntity, Long> {

}
