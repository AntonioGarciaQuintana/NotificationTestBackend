package com.gila.notificationtest;

import com.gila.notificationtest.domain.entity.NotificationTypeEntity;
import com.gila.notificationtest.domain.enums.NotificationType;
import com.gila.notificationtest.service.impl.NotificationTypeValidatorService;
import com.gila.notificationtest.service.interfaces.NotificationTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationTypeValidatorServiceTest {

    @Mock
    private NotificationTypeService typeService;

    @InjectMocks
    private NotificationTypeValidatorService validatorService;

    private NotificationTypeEntity emailType;
    private NotificationTypeEntity smsType;

    @BeforeEach
    void setUp() {
        emailType = new NotificationTypeEntity((short)1, "EMAIL", "Email notifications", true);
        smsType = new NotificationTypeEntity((short)2, "SMS", "SMS notifications", true);

        when(typeService.getAllTypes()).thenReturn(List.of(emailType, smsType));

        validatorService = new NotificationTypeValidatorService(typeService);
    }

    @Test
    void validateAndGet_shouldReturnEntity_whenTypeExists() {
        NotificationTypeEntity result = validatorService.validateAndGet(NotificationType.EMAIL);

        assertNotNull(result);
        assertEquals("EMAIL", result.getCode());
        assertEquals("Email notifications", result.getDescription());
    }

    @Test
    void validateAndGet_shouldThrowException_whenTypeDoesNotExist() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validatorService.validateAndGet(NotificationType.PUSH)
        );

        assertEquals("Invalid notification type: PUSH", exception.getMessage());
    }
}
