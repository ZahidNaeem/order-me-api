package com.alfauz.orderme.enumeration;

import com.alfauz.orderme.exception.UserTypeNotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum UserType {
    BUSINESS("business"),
    CUSTOMER("customer");

    private String value;

    UserType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static UserType fromValue(final String value) {
        return Arrays.stream(UserType.values())
                .filter(userType -> userType.getValue().equals(value))
                .findFirst()
                .orElseThrow(
                        () -> new UserTypeNotFoundException("User type with value " + value + " not found")
                );
    }

}
