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
@Table(name = "COUNTRY_CODES", schema = "OM")
public class CountryCodeEntity {

    @Id
    @Column(name = "COUNTRY_ID", precision = 0)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "COUNTRY")
    private String country;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(name = "CODE")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryCode")
    @Cascade(value = CascadeType.ALL)
    private List<UserEntity> users;
}
