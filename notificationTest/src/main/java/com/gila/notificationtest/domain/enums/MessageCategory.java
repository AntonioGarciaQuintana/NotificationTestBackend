package com.gila.notificationtest.domain.enums;

import lombok.Getter;

@Getter
public enum MessageCategory {
    SPORTS("SPORTS", "Sports category notifications"),
    FINANCE("FINANCE", "Finance category notifications"),
    MOVIES("MOVIES", "Movies category notifications");

    private final String code;
    private final String description;

    MessageCategory(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
