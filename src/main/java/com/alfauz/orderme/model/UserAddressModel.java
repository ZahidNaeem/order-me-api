package com.alfauz.orderme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressModel {
    private Long id;

    @NotBlank(message = "Address must not be blank")
    @Size(min = 2, max = 200, message = "Address should has min. 2 and max. 200 characters")
    private String address;

    @NotBlank(message = "Address Type must not be blank")
    @Size(min = 2, max = 20, message = "Address should has min. 2 and max. 20 characters")
    private String addressType;

    private Long user;
}
