package com.gila.notificationtest.service.interfaces;

import com.gila.notificationtest.domain.enums.NotificationType;

public interface NotificationChannelSenderService {
    void send(NotificationType type, String message);
}
