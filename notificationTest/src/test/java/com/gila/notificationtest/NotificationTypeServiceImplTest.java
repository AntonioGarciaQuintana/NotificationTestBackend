package com.gila.notificationtest;

import com.gila.notificationtest.domain.entity.NotificationTypeEntity;
import com.gila.notificationtest.repository.NotificationTypeRepository;
import com.gila.notificationtest.service.impl.NotificationTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationTypeServiceImplTest {

    @Mock
    private NotificationTypeRepository repository;

    @InjectMocks
    private NotificationTypeServiceImpl service;

    private NotificationTypeEntity emailType;
    private NotificationTypeEntity smsType;

    @BeforeEach
    void setUpModels() {
        emailType = new NotificationTypeEntity();
        emailType.setId((short) 1);
        emailType.setCode("EMAIL");
        emailType.setDescription("Email notifications");
        emailType.setActive(true);

        smsType = new NotificationTypeEntity();
        smsType.setId((short) 2);
        smsType.setCode("SMS");
        smsType.setDescription("SMS notifications");
        smsType.setActive(true);
    }

    @Test
    void getTypeEntity_shouldReturnEmailType_whenExists() {
        when(repository.findByCode("EMAIL")).thenReturn(Optional.of(emailType));

        NotificationTypeEntity result = service.getTypeEntity("EMAIL");

        assertNotNull(result);
        assertEquals("EMAIL", result.getCode());
        assertEquals("Email notifications", result.getDescription());
        verify(repository, times(1)).findByCode("EMAIL");
    }

    @Test
    void getTypeEntity_shouldThrowException_whenTypeDoesNotExist() {
        when(repository.findByCode("INVALID")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.getTypeEntity("INVALID")
        );
        assertEquals("Invalid notification type", exception.getMessage());
        verify(repository, times(1)).findByCode("INVALID");
    }

    @Test
    void getAllTypes_shouldReturnAllTypes() {
        when(repository.findAll()).thenReturn(List.of(emailType, smsType));

        List<NotificationTypeEntity> result = service.getAllTypes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(emailType));
        assertTrue(result.contains(smsType));
        verify(repository, times(1)).findAll();
    }
}
