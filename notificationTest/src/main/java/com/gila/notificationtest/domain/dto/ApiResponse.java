package com.gila.notificationtest.domain.dto;

import com.gila.notificationtest.domain.enums.MessageCategory;


public record ApiResponse(
        MessageCategory category,
        String message,
        int sentNotifications,
        String status
) { }
