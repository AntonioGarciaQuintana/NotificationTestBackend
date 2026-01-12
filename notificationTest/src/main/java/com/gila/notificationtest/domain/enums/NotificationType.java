package com.gila.notificationtest.domain.enums;

import lombok.Getter;

@Getter
public enum NotificationType {
    SMS("SMS", "SMS notification"),
    EMAIL("EMAIL", "E-mail notification"),
    PUSH("PUSH", "Push notification");

    private final String code;
    private final String description;

    NotificationType(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
