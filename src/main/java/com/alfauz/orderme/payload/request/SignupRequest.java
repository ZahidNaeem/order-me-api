package com.alfauz.orderme.payload.request;

import com.alfauz.orderme.entity.BankEntity;
import com.alfauz.orderme.enumeration.ActivationStatus;
import com.alfauz.orderme.enumeration.RoleName;
import com.alfauz.orderme.enumeration.UserType;
import com.alfauz.orderme.model.RoleModel;
import com.alfauz.orderme.model.UserMainCategoryModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @NotBlank(message = "Address must not be blank")
    @Size(min = 3, max = 50, message = "Address should has min. 3 and max. 500 characters")
    private String address;

    @Size(max = 50, message = "Email should ha max. 50 characters")
    private String branchName;

    private Long bank;

    @Size(max = 24, message = "Bank Acc. No. should has max. 50 characters")
    private String bankAccountNo;

    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 50, message = "Username should has min. 3 and max. 50 characters")
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 100, message = "Password should has min. 6 and max. 100 characters")
    private String password;

    @Size(max = 50, message = "Credit card should has max. 50 characters")
    private String creditCardNo;

    private Date ccExpiryDate;

    @Size(max = 10, message = "CVC should has max. 10 characters")
    @Column(name = "CVC")
    private String cvc;

    @NotNull(message = "User type must not be null")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotNull(message = "activation status must not be null")
    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus;

    @Size(max = 2000, message = "Credit card should has max. 2000 characters")
    private String remarks;

    @Valid
    private List<UserMainCategoryModel> userMainCategories;

    private Set<RoleName> roles;
}
