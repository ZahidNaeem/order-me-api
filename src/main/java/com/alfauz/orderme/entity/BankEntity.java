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
@Table(name = "BANKS", schema = "OM", uniqueConstraints = {
        @UniqueConstraint(name = "banks_uk1", columnNames = {"BANK"}),
        @UniqueConstraint(name = "banks_uk2", columnNames = {"LOGO"})
})
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BANK_ID", precision = 0)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "BANK")
    private String bank;

    @Size(max = 50)
    @Column(name = "LOGO")
    private String logo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bank")
    @Cascade(value = CascadeType.ALL)
    private List<UserEntity> users;
}
