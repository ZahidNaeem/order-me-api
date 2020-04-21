package com.alfauz.orderme.payload.request;

import com.alfauz.orderme.model.RoleModel;
import com.alfauz.orderme.model.UserAddressModel;
import com.alfauz.orderme.model.UserSaleItemCategoryModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank(message = "First name must not be blank")
    @Size(min = 1, max = 20, message = "First name should has min. 1 and max. 20 characters")
    private String firstName;

    @Size(max = 20, message = "Middle name should has max. 20 characters")
    private String middleName;

    @NotBlank(message = "Last name must not be blank")
    @Size(max = 20, message = "Last name should has max. 20 characters")
    private String lastName;

    @NotNull
    private Long countryCode;

    @NotBlank
    @Size(min = 6, max = 50, message = "Phone should has min. 6 and max. 50 characters")
    private String phone;

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

    @Size(max = 50, message = "Credit card should has max. 50 characters")
    private String creditCardNo;

    private Date ccExpiryDate;

    @NotBlank(message = "User type must not be blank")
    @Size(min = 1, max = 20, message = "User type should has min. 1 and max. 20 characters")
    private String userType;

    @NotBlank
    private String activationStatus;

    @Size(max = 2000, message = "Credit card should has max. 2000 characters")
    private String remarks;

    @Valid
    private List<UserAddressModel> userAddresses;

    @Valid
    private List<UserSaleItemCategoryModel> userSaleItemCategories;

    private Set<RoleModel> roles;
}
