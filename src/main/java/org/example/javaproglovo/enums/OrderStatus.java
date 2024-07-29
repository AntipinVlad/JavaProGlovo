package org.example.javaproglovo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    IN_PROCESSING("In processing"),
    SENT("Sent"),
    DELIVERED("Delivered");

    private final String message;
}
