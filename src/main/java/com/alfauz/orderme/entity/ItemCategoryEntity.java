package com.alfauz.orderme.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM_CATEGORIES", schema = "OM")
public class ItemCategoryEntity {

    @Id
    @Size(min = 2, max = 50, message = "Item category should has min. 2 and max. 50 characters")
    @Column(name = "ITEM_CATEGORY")
    private String itemCategory;

    @NotBlank
    @Size(min = 2, max = 50, message = "Main category should has min. 2 and max. 50 characters")
    @Column(name = "MAIN_CATEGORY")
    private String mainCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemCategory")
    @Cascade(value = CascadeType.ALL)
    private List<UserSaleItemCategoryEntity> userSaleItemCategories;
}
