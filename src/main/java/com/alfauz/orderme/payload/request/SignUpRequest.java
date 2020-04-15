package com.alfauz.orderme.payload.request;

import com.alfauz.orderme.model.UserAddressModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "First name must not be blank")
    @Size(min = 1, max = 20, message = "First name should has min. 1 and max. 20 characters")
    private String firstName;

    @Size(max = 20, message = "Middle name should has max. 20 characters")
    private String middleName;

    @NotBlank(message = "Last name must not be blank")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotBlank(message = "Email must not be blank")
    @Size(min = 6, max = 50, message = "Email should has min. 6 and max. 50 characters")
    @Email
    private String email;

    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 50, message = "Username should has min. 3 and max. 50 characters")
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 100, message = "Password should has min. 6 and max. 100 characters")
    private String password;

    @NotBlank(message = "User type must not be blank")
    @Size(min = 1, max = 20, message = "User type should has min. 1 and max. 20 characters")
    private String userType;

    private List<UserAddressModel> userAddresses;

    private Set<String> roles;
}
