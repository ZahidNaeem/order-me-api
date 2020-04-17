package com.alfauz.orderme.enumeration;

import com.alfauz.orderme.exception.AddressTypeNotFoundException;

import java.util.Arrays;

public enum AddressType {
    HOME_ADDRESS("home"),
    OFFICE_ADDRESS("office"),
    OTHER_ADDRESS("other");

    private String value;

    public String getValue() {
        return value;
    }

    AddressType(final String value) {
        this.value = value;
    }

    public static AddressType fromValue(final String value) {
        return Arrays.stream(AddressType.values())
                .filter(addressType -> addressType.getValue().equals(value))
                .findFirst()
                .orElseThrow(
                        () -> new AddressTypeNotFoundException("Address type with value " + value + " not found")
                );
    }
}
