package com.gila.notificationtest.service.impl;

import com.gila.notificationtest.domain.dto.NotificationRequest;
import com.gila.notificationtest.domain.entity.MessageCategoryEntity;
import com.gila.notificationtest.domain.entity.NotificationTypeEntity;
import com.gila.notificationtest.domain.entity.SentNotificationEntity;
import com.gila.notificationtest.domain.entity.UserEntity;
import com.gila.notificationtest.domain.enums.NotificationType;
import com.gila.notificationtest.repository.SentNotificationRepository;
import com.gila.notificationtest.service.interfaces.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NotificationService {

    private final UserInterfaceService userService;
    private final MessageCategoryService messageCategoryService;
    private final NotificationTypeValidatorService notificationTypeValidatorService;
    private final NotificationChannelSenderService notificationChannelSenderService;
    private final SentNotificationRepository sendNotificationRepository;

    public NotificationService(UserInterfaceService userService,
                               MessageCategoryService messageCategoryService,
                               NotificationTypeValidatorService notificationTypeValidatorService,
                               NotificationChannelSenderService notificationChannelSenderService,
                               SentNotificationRepository sendNotificationRepository
                                   ){
        this.userService = userService;
        this.messageCategoryService = messageCategoryService;
        this.notificationTypeValidatorService = notificationTypeValidatorService;
        this.notificationChannelSenderService = notificationChannelSenderService;
        this.sendNotificationRepository = sendNotificationRepository;
    }

    /**
     * Sends a notification to all users subscribed to the given category
     * through their configured notification channels.
     *
     * For each user and channel, a delivery attempt is made and the result
     * is stored in the notification log.
     *
     * @param notification notification request containing category and message
     * @return total number of notification attempts performed
     */
    @Transactional
    public int send(NotificationRequest notification) {

        MessageCategoryEntity category  = this.messageCategoryService.getCategoryEntity(notification.getCategory().name());
        List<UserEntity> users = userService.getSubscribedUsers(notification.getCategory());

        List<SentNotificationEntity> sentNotifications = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();

        for (UserEntity user : users) {
            log.info("Sending notifications to user: {}", user.getName());
            for (NotificationType channel : user.getChannels()) {

                NotificationTypeEntity notificationType = this.notificationTypeValidatorService.validateAndGet(channel);

                boolean delivered = trySendNotification(channel, notification.getMessage(), user.getName());

                sentNotifications.add(buildSentNotification(user, notification, category, notificationType, now, delivered));
            }
        }

        sendNotificationRepository.saveAll(sentNotifications);
        return sentNotifications.size();
    }


    /**
     * Attempts to send a notification through the given channel.
     *
     * @param channel notification channel to use
     * @param message message to be sent
     * @param userName recipient userName (used for logging)
     * @return {@code true} if the notification was sent successfully,
     *         {@code false} otherwise
     */
    private boolean trySendNotification(NotificationType channel, String message, String userName) {
        try {
            this.notificationChannelSenderService.send(channel, message);
            return true;
        } catch (Exception e) {
            log.error("Failed to send notification to user {} via {}: {}", userName, channel, e.getMessage(), e);
            return false;
        }
    }

    /**
     * Builds a {@link SentNotificationEntity} representing a notification
     * delivery attempt.
     *
     * @param user recipient user
     * @param notification original notification request
     * @param categoryEntity message category entity
     * @param typeEntity notification type entity
     * @param now timestamp of the attempt
     * @param delivered delivery result
     * @return populated {@link SentNotificationEntity}
     */
    public SentNotificationEntity buildSentNotification(UserEntity user,
                                                       NotificationRequest notification,
                                                       MessageCategoryEntity categoryEntity,
                                                       NotificationTypeEntity typeEntity,
                                                        LocalDateTime now,
                                                        boolean delivered) {
        return SentNotificationEntity.builder()
                .type(typeEntity)
                .category(categoryEntity)
                .message(notification.getMessage())
                .userId(user.getId())
                .userName(user.getName())
                .userEmail(user.getEmail())
                .userPhone(user.getPhoneNumber())
                .timestamp(now)
                .delivered(delivered)
                .build();
    }
}
