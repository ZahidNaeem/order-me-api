package com.alfauz.orderme.enumeration;

import com.alfauz.orderme.exception.RoleNameNotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum RoleName {
    ROLE_PM("pm"),
    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private String value;

    RoleName(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static RoleName fromValue(final String value) {
        return Arrays.stream(RoleName.values())
                .filter(role -> value.equals(role.getValue()))
                .findFirst()
                .orElseThrow(
                        () -> new RoleNameNotFoundException("Role with value '" + value + "' not found"));
    }
}
