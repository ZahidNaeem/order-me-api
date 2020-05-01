package com.alfauz.orderme.model;

import com.alfauz.orderme.enumeration.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName name;
}
