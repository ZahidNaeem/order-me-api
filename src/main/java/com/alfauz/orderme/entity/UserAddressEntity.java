package com.alfauz.orderme.entity;

import com.alfauz.orderme.enumeration.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_ADDRESSES", schema = "OM")
public class UserAddressEntity extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Address must not be blank")
    @Size(min = 1, max = 200, message = "Address should has min. 1 and max. 200 characters")
    @Column(name = "ADDRESS")
    private String address;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Address Type must not be blank")
    @Size(min = 1, max = 20, message = "Address should has min. 1 and max. 20 characters")
    @Column(name = "ADDRESS_TYPE")
    private AddressType addressType;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}
