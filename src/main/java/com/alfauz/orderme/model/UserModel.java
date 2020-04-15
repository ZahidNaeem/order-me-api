package com.alfauz.orderme.model;

import com.alfauz.orderme.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    private String username;
    private String email;
    private String password;
    private String userType;
    private List<UserAddressModel> userAddresses;
    private Set<RoleModel> roles = new HashSet<>();
}
