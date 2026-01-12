package com.gila.notificationtest;

import com.gila.notificationtest.domain.dto.SentNotificationDTO;
import com.gila.notificationtest.domain.entity.MessageCategoryEntity;
import com.gila.notificationtest.domain.entity.NotificationTypeEntity;
import com.gila.notificationtest.domain.entity.SentNotificationEntity;
import com.gila.notificationtest.repository.SentNotificationRepository;
import com.gila.notificationtest.service.impl.SentNotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SentNotificationServiceImplTest {

    @Mock
    private SentNotificationRepository repository;

    @InjectMocks
    private SentNotificationServiceImpl service;

    private SentNotificationEntity sent1;
    private SentNotificationEntity sent2;

    @BeforeEach
    void setUp() {
        NotificationTypeEntity typeEmail = new NotificationTypeEntity((short)1, "EMAIL", "Email notifications", true);
        MessageCategoryEntity categoryEmail = new MessageCategoryEntity((short)1, "INFO", "Information", true);

        sent1 = SentNotificationEntity.builder()
                .id(1L)
                .type(typeEmail)
                .category(categoryEmail)
                .message("Mensaje 1")
                .userId(100L)
                .userName("User1")
                .userEmail("user1@example.com")
                .userPhone("123456789")
                .timestamp(LocalDateTime.now())
                .delivered(true)
                .build();

        sent2 = SentNotificationEntity.builder()
                .id(2L)
                .type(typeEmail)
                .category(categoryEmail)
                .message("Mensaje 2")
                .userId(101L)
                .userName("User2")
                .userEmail("user2@example.com")
                .userPhone("987654321")
                .timestamp(LocalDateTime.now().minusHours(1))
                .delivered(false)
                .build();
    }

    @Test
    void getAllSentNotifications_shouldReturnDTOsInCorrectOrder() {
        when(repository.findAllByOrderByTimestampDesc()).thenReturn(List.of(sent1, sent2));

        List<SentNotificationDTO> result = service.getAllSentNotifications();

        assertNotNull(result);
        assertEquals(2, result.size());

        SentNotificationDTO dto1 = result.getFirst();
        assertEquals(sent1.getId(), dto1.id());
        assertEquals(sent1.getType().getCode(), dto1.typeCode());
        assertEquals(sent1.getCategory().getCode(), dto1.categoryCode());
        assertEquals(sent1.getMessage(), dto1.message());
        assertEquals(sent1.getUserId(), dto1.userId());
        assertEquals(sent1.isDelivered(), dto1.delivered());

        SentNotificationDTO dto2 = result.get(1);
        assertEquals(sent2.getId(), dto2.id());

        // Verificar que el repositorio fue llamado exactamente 1 vez
        verify(repository, times(1)).findAllByOrderByTimestampDesc();
    }
}
