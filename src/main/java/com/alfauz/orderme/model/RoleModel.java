package com.alfauz.orderme.model;

import com.alfauz.orderme.enumeration.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    private Long id;
    private RoleName name;
}
