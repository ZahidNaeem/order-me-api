package com.alfauz.orderme.enumeration;

import com.alfauz.orderme.exception.RoleNameNotFoundException;

import java.util.Arrays;

public enum RoleName {
    ROLE_PM("pm"),
    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private String value;

    RoleName(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleName fromValue(final String value) {
        return Arrays.stream(RoleName.values())
                .filter(role -> value.equals(role.getValue()))
                .findFirst()
                .orElseThrow(
                        () -> new RoleNameNotFoundException("Role with value '" + value + "' not found"));
    }
}
