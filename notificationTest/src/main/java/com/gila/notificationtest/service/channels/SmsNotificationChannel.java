package com.gila.notificationtest.service.channels;

import com.gila.notificationtest.domain.enums.NotificationType;
import com.gila.notificationtest.service.interfaces.NotificationChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsNotificationChannel implements NotificationChannelService {

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }

    @Override
    public void send(String message) {
        log.info("Sending SMS. Message: {}", message);
    }
}
