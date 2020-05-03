package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends JpaRepository<BankEntity, Long> {
}
