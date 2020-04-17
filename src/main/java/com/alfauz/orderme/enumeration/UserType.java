package com.alfauz.orderme.enumeration;

import com.alfauz.orderme.exception.UserTypeNotFoundException;

import java.util.Arrays;

public enum UserType {
    SHOP_OWNER("shop owner"),
    CUSTOMER("customer");

    private String value;

    UserType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserType fromValue(final String value) {
        return Arrays.stream(UserType.values())
                .filter(userType -> userType.getValue().equals(value))
                .findFirst()
                .orElseThrow(
                        () -> new UserTypeNotFoundException("User type with value " + value + " not found")
                );
    }

}
