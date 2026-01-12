package com.gila.notificationtest.service.interfaces;

import com.gila.notificationtest.domain.dto.SentNotificationDTO;

import java.util.List;

public interface SentNotificationService {
    List<SentNotificationDTO> getAllSentNotifications();
}
