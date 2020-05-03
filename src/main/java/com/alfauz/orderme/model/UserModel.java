package com.alfauz.orderme.model;

import com.alfauz.orderme.entity.Auditable;
import com.alfauz.orderme.enumeration.ActivationStatus;
import com.alfauz.orderme.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends Auditable<Long> {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long countryCode;
    private String phone;
    private String email;
    private String address;
    private String branchName;
    private Long bank;
    private String bankAccountNo;
    private String username;
    private String password;
    private String creditCardNo;
    private Date ccExpiryDate;
    private String cvc;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus;
    private String remarks;
    private List<UserMainCategoryModel> userMainCategories;
    private Set<RoleModel> roles;
}
