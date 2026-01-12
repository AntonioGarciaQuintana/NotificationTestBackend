package com.gila.notificationtest.service.interfaces;

import com.gila.notificationtest.domain.enums.NotificationType;

public interface NotificationChannelService {

    NotificationType getType();

    void send(String message);
}
