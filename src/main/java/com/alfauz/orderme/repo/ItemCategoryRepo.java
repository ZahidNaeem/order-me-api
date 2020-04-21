package com.alfauz.orderme.repo;

import com.alfauz.orderme.entity.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepo extends JpaRepository<ItemCategoryEntity, String> {

}
