package com.gila.notificationtest.service.interfaces;

import com.gila.notificationtest.domain.entity.NotificationTypeEntity;

import java.util.List;

public interface NotificationTypeService {
    NotificationTypeEntity getTypeEntity(String code);
    List<NotificationTypeEntity> getAllTypes();
}
