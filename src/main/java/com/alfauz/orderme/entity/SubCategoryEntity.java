package com.alfauz.orderme.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SUB_CATEGORIES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"SUB_CAT_NAME"})})
public class SubCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SUB_CAT_ID")
    private Long id;

    @NotBlank
    @Column(name = "SUB_CAT_NAME")
    @Size(max = 50, message = "Sub category name should has max. 50 characters")
    private String subCatName;

    @NotNull
    @JoinColumn(name = "MAIN_CAT_ID", referencedColumnName = "MAIN_CAT_ID")
    @ManyToOne(optional = false)
    private MainCategoryEntity mainCategory;
}
