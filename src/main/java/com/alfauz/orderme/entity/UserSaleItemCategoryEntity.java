package com.alfauz.orderme.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_SALE_ITEM_CATS", schema = "OM", uniqueConstraints = {
        @UniqueConstraint(name = "user_sale_item_cats_uk1", columnNames = {"USER_ID", "ITEM_CATEGORY"})
})
public class UserSaleItemCategoryEntity extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_CATEGORY")
    private ItemCategoryEntity itemCategory;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}
