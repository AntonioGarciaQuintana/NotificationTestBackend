package com.gila.notificationtest.service.impl;

import com.gila.notificationtest.domain.enums.NotificationType;
import com.gila.notificationtest.service.factory.NotificationChannelFactory;
import com.gila.notificationtest.service.interfaces.NotificationChannelSenderService;
import com.gila.notificationtest.service.interfaces.NotificationChannelService;
import org.springframework.stereotype.Service;

@Service
public class DefaultNotificationChannelSenderImpl implements NotificationChannelSenderService {

    private final NotificationChannelFactory factory;

    public DefaultNotificationChannelSenderImpl(NotificationChannelFactory factory) {
        this.factory = factory;
    }


    /**
     * Sends a notification message using the channel associated
     * with the given notification type.
     *
     * @param type notification channel type
     * @param message message to be sent
     */
    @Override
    public void send(NotificationType type, String message) {
        NotificationChannelService channel = factory.getChannel(type);
        channel.send(message);
    }
}
