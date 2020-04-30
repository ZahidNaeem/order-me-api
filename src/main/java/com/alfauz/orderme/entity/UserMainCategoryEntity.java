package com.alfauz.orderme.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_MAIN_CATEGORIES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USER_ID", "MAIN_CAT_ID"})})
public class UserMainCategoryEntity extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @JoinColumn(name = "MAIN_CAT_ID", referencedColumnName = "MAIN_CAT_ID")
    @ManyToOne(optional = false)
    private MainCategoryEntity mainCategory;

    @NotNull
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UserEntity user;
}
