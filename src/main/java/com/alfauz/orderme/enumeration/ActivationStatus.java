package com.alfauz.orderme.enumeration;

import com.alfauz.orderme.exception.ActivationStatusNotFoundException;

import java.util.Arrays;

public enum ActivationStatus {

    PENDING("pending"),
    ACTIVE("active"),
    INACTIVE("inactive"),
    REJECTED("rejected");

    private String value;

    ActivationStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ActivationStatus fromValue(final String value) {
        return Arrays.stream(ActivationStatus.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new ActivationStatusNotFoundException("Activation Status with value " + value + " not found"));
    }
}
