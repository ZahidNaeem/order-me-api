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
@Table(name = "COUNTRY_CODES", schema = "OM", uniqueConstraints = {
        @UniqueConstraint(name = "country_codes_uk1", columnNames = {"COUNTRY"})
})
public class CountryCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COUNTRY_ID", precision = 0)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "COUNTRY")
    private String country;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(name = "ALPHA2_CODE")
    private String alpha2Code;

    @Size(min = 2, max = 10)
    @Column(name = "ALPHA3_CODE")
    private String alpha3Code;

    @Size(min = 2, max = 10)
    @Column(name = "CAPITAL_CITY")
    private String capitalCity;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(name = "DIALING_CODE")
    private String dialingCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryCode")
    @Cascade(value = CascadeType.ALL)
    private List<UserEntity> users;
}
