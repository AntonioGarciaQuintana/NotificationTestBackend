package com.gila.notificationtest;

import com.gila.notificationtest.domain.dto.NotificationRequest;
import com.gila.notificationtest.domain.entity.MessageCategoryEntity;
import com.gila.notificationtest.domain.entity.NotificationTypeEntity;
import com.gila.notificationtest.domain.entity.UserEntity;
import com.gila.notificationtest.domain.enums.MessageCategory;
import com.gila.notificationtest.domain.enums.NotificationType;
import com.gila.notificationtest.repository.SentNotificationRepository;
import com.gila.notificationtest.service.impl.NotificationService;
import com.gila.notificationtest.service.impl.NotificationTypeValidatorService;
import com.gila.notificationtest.service.interfaces.MessageCategoryService;
import com.gila.notificationtest.service.interfaces.NotificationChannelSenderService;
import com.gila.notificationtest.service.interfaces.UserInterfaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private UserInterfaceService userService;

    @Mock
    private MessageCategoryService messageCategoryService;

    @Mock
    private NotificationTypeValidatorService validatorService;

    @Mock
    private NotificationChannelSenderService channelSender;

    @Mock
    private SentNotificationRepository repository;

    @InjectMocks
    private NotificationService notificationService;

    private NotificationRequest request;
    private UserEntity user1;
    private UserEntity user2;
    private MessageCategoryEntity categoryEntity;
    private NotificationTypeEntity typeEntity;

    @BeforeEach
    void setUp() {
        request = new NotificationRequest(MessageCategory.SPORTS, "Test Message");

        user1 = new UserEntity();
        user1.setId(1L);
        user1.setName("User1");
        user1.setEmail("user1@example.com");
        user1.setPhoneNumber("12345");
        user1.setChannels(List.of(NotificationType.EMAIL, NotificationType.SMS));
        user1.setSubscribedCategories(List.of(MessageCategory.SPORTS));

        user2 = new UserEntity();
        user2.setId(2L);
        user2.setName("User2");
        user2.setEmail("user2@example.com");
        user2.setPhoneNumber("67890");
        user2.setChannels(List.of(NotificationType.EMAIL));
        user2.setSubscribedCategories(List.of(MessageCategory.SPORTS));

        categoryEntity = new MessageCategoryEntity((short)1, "EMAIL", "Email category", true);

        typeEntity = new NotificationTypeEntity((short)1, "EMAIL", "Email type", true);

        when(messageCategoryService.getCategoryEntity("SPORTS")).thenReturn(categoryEntity);
        when(userService.getSubscribedUsers(MessageCategory.SPORTS)).thenReturn(List.of(user1, user2));
        when(validatorService.validateAndGet(any(NotificationType.class))).thenReturn(typeEntity);
    }


    @Test
    void send_shouldSendNotificationsAndReturnCorrectCount() {
        int sentCount = notificationService.send(request);

        assertEquals(3, sentCount);

        verify(messageCategoryService, times(1)).getCategoryEntity("SPORTS");
        verify(userService, times(1)).getSubscribedUsers(MessageCategory.SPORTS);
        verify(validatorService, times(3)).validateAndGet(any(NotificationType.class));
        verify(channelSender, times(3)).send(any(NotificationType.class), eq("Test Message"));
        verify(repository, times(1)).saveAll(anyList());
    }

    @Test
    void send_shouldMarkDeliveredFalseIfChannelFails() {
        doThrow(new RuntimeException("Channel error"))
                .when(channelSender).send(NotificationType.EMAIL, "Test Message");

        int sentCount = notificationService.send(request);

        assertEquals(3, sentCount);

        verify(channelSender, times(3)).send(any(NotificationType.class), eq("Test Message"));

        verify(repository, times(1)).saveAll(anyList());
    }

}
