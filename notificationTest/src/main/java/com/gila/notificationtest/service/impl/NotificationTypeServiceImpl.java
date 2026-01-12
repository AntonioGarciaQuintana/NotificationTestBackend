package com.gila.notificationtest.service.impl;

import com.gila.notificationtest.domain.entity.NotificationTypeEntity;
import com.gila.notificationtest.repository.NotificationTypeRepository;
import com.gila.notificationtest.service.interfaces.NotificationTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private final NotificationTypeRepository notificationTypeRepository;

    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository){
        this.notificationTypeRepository = notificationTypeRepository;
    }

    /**
     * Retrieves a notification type entity by its code.
     *
     * @param code notification type code
     * @return notification type entity
     * @throws IllegalArgumentException if the type does not exist
     */
    @Override
    @Transactional(readOnly = true)
    public NotificationTypeEntity getTypeEntity(String code) {
        return notificationTypeRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notification type"));
    }

    /**
     * Retrieves all available notification types.
     *
     * @return list of notification type entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificationTypeEntity> getAllTypes() {
        return notificationTypeRepository.findAll();
    }
}
