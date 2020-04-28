package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCategoryRepo extends JpaRepository<MainCategoryEntity, Long> {
}
