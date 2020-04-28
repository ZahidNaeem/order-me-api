package com.alfauz.orderme.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MAIN_CATEGORIES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"MAIN_CAT_NAME"})})
public class MainCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAIN_CAT_ID")
    private Long id;
    @Column(name = "MAIN_CAT_NAME")
    private String mainCatName;
    @OneToMany(mappedBy = "mainCategory")
    @Cascade(CascadeType.ALL)
    private List<UserMainCategoryEntity> userMainCategories;

    @OneToMany(mappedBy = "mainCategory")
    @Cascade(CascadeType.ALL)
    private List<SubCategoryEntity> subCategories;
}
