package com.alfauz.orderme.model;

import com.alfauz.orderme.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String username;
    private String password;
    private String creditCardNo;
    private Date ccExpiryDate;
    private String userType;
    private String activationStatus;
    private String remarks;
    private List<UserMainCategoryModel> userMainCategories;
    private Set<RoleModel> roles;
}
