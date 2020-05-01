package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.MainCategoryEntity;
import com.alfauz.orderme.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategoryEntity, Long> {

    List<SubCategoryEntity> findAllByMainCategory(final MainCategoryEntity mainCategory);
}
