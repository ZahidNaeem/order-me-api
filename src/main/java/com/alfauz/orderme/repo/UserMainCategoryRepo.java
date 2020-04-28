package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.UserMainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMainCategoryRepo extends JpaRepository<UserMainCategoryEntity, Long> {
}
