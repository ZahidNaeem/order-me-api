package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.CountryCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryCodeRepo extends JpaRepository<CountryCodeEntity, Long> {
}
