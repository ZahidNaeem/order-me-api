package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategoryEntity, Long> {
}
