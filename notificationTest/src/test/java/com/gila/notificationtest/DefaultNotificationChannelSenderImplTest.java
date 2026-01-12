package com.gila.notificationtest;

import com.gila.notificationtest.domain.enums.NotificationType;
import com.gila.notificationtest.service.factory.NotificationChannelFactory;
import com.gila.notificationtest.service.impl.DefaultNotificationChannelSenderImpl;
import com.gila.notificationtest.service.interfaces.NotificationChannelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultNotificationChannelSenderImplTest {

    @Mock
    private NotificationChannelFactory factory;

    @Mock
    private NotificationChannelService channelService;

    @InjectMocks
    private DefaultNotificationChannelSenderImpl sender;

    private NotificationType type;
    private String message;

    @BeforeEach
    void setUp() {
        type = NotificationType.EMAIL;
        message = "Test notification message";

        when(factory.getChannel(type)).thenReturn(channelService);
    }

    @Test
    void send_shouldCallChannelSendOnce() {
        sender.send(type, message);

        verify(factory, times(1)).getChannel(type);
        verify(channelService, times(1)).send(message);
    }

    @Test
    void send_shouldThrowExceptionIfFactoryReturnsNull() {
        when(factory.getChannel(type)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> sender.send(type, message));

        verify(factory, times(1)).getChannel(type);
    }
}
