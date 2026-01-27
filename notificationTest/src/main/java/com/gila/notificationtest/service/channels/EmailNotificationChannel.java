package com.gila.notificationtest.service.channels;

import com.gila.notificationtest.domain.enums.NotificationType;
import com.gila.notificationtest.service.SnsPublisher;
import com.gila.notificationtest.service.interfaces.NotificationChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailNotificationChannel implements NotificationChannelService {

    private final SnsPublisher snsPublisher;

    @Value("${AWS_ARN_NOTIFICATION}")
    private String notificationTopicArn;

    EmailNotificationChannel(SnsPublisher snsPublisher){
        this.snsPublisher = snsPublisher;
    }
    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    @Override
    public void send(String message) {
        snsPublisher.publish(
            this.notificationTopicArn,
                "New Notification",
                message
        );
        log.info("Sending Email notification. Message: {}", message);
    }
}
