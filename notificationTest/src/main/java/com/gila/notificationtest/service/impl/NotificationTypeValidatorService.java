package com.gila.notificationtest.service.impl;

import com.gila.notificationtest.domain.entity.NotificationTypeEntity;
import com.gila.notificationtest.service.interfaces.NotificationTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NotificationTypeValidatorService {

    private final NotificationTypeService notificationTypeService;
    private Map<String, NotificationTypeEntity> notificationTypeMap;

    public NotificationTypeValidatorService(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
        this.notificationTypeMap = buildNotificationTypeMap();
    }

    /**
     * Builds a map of notification types indexed by their code.
     *
     * @return map of notification type code to entity
     */
    @Transactional(readOnly = true)
    private Map<String, NotificationTypeEntity> buildNotificationTypeMap() {
        return notificationTypeService.getAllTypes()
                .stream()
                .collect(Collectors.toMap(NotificationTypeEntity::getCode, Function.identity()));
    }

    /**
     * Validates the given notification type and returns its corresponding entity.
     *
     * @param type notification type enum
     * @return notification type entity
     * @throws IllegalArgumentException if the type is not valid
     */
    @Transactional(readOnly = true)
    public NotificationTypeEntity validateAndGet(com.gila.notificationtest.domain.enums.NotificationType type) {
        NotificationTypeEntity entity = notificationTypeMap.get(type.name());
        if (entity == null) {
            throw new IllegalArgumentException("Invalid notification type: " + type.name());
        }
        return entity;
    }
}
