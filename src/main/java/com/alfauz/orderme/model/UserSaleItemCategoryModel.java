package com.alfauz.orderme.model;

import com.alfauz.orderme.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSaleItemCategoryModel extends Auditable<Long> {
    private Long id;
    private String itemCategory;
    private Long user;
}
