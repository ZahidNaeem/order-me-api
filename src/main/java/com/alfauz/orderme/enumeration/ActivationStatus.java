package com.alfauz.orderme.enumeration;

import com.alfauz.orderme.exception.ActivationStatusNotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ActivationStatus fromValue(final String value) {
        return Arrays.stream(ActivationStatus.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new ActivationStatusNotFoundException("Activation Status with value " + value + " not found"));
    }
}
