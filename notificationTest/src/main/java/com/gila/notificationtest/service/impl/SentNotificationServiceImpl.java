package com.gila.notificationtest.service.impl;

import com.gila.notificationtest.domain.dto.SentNotificationDTO;
import com.gila.notificationtest.domain.entity.SentNotificationEntity;
import com.gila.notificationtest.repository.SentNotificationRepository;
import com.gila.notificationtest.service.interfaces.SentNotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SentNotificationServiceImpl implements SentNotificationService {

    private final SentNotificationRepository sentNotificationRepository;

    public SentNotificationServiceImpl(SentNotificationRepository sentNotificationRepository){
        this.sentNotificationRepository = sentNotificationRepository;
    }

    /**
     * Retrieves all sent notifications ordered by most recent first.
     *
     * @return list of sent notification DTOs
     */
    @Override
    @Transactional(readOnly = true)
    public List<SentNotificationDTO> getAllSentNotifications() {
        return this.sentNotificationRepository.findAllByOrderByTimestampDesc()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    /**
     * Maps a SentNotificationEntity to a SentNotificationDTO.
     *
     * @param entity notification entity
     * @return mapped DTO
     */
    private SentNotificationDTO toDTO(SentNotificationEntity entity) {
        return new SentNotificationDTO(
                entity.getId(),
                entity.getType().getCode(),
                entity.getType().getDescription(),
                entity.getCategory().getCode(),
                entity.getCategory().getDescription(),
                entity.getMessage(),
                entity.getUserId(),
                entity.getUserName(),
                entity.getUserEmail(),
                entity.getUserPhone(),
                entity.getTimestamp(),
                entity.isDelivered()
        );
    }

}
