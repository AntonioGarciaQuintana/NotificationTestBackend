package com.gila.notificationtest.domain.dto;

import java.time.LocalDateTime;

public record SentNotificationDTO(
        Long id,
        String typeCode,
        String typeDescription,
        String categoryCode,
        String categoryDescription,
        String message,
        Long userId,
        String userName,
        String userEmail,
        String userPhone,
        LocalDateTime timestamp,
        boolean delivered
) {}