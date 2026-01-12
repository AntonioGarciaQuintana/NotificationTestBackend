package com.gila.notificationtest.service.factory;

import com.gila.notificationtest.domain.enums.NotificationType;
import com.gila.notificationtest.service.interfaces.NotificationChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class NotificationChannelFactory {

    private final Map<NotificationType, NotificationChannelService> channels;

    public NotificationChannelFactory(List<NotificationChannelService> channelList) {
        this.channels = channelList.stream()
                .collect(Collectors.toMap(
                        NotificationChannelService::getType,
                        Function.identity()
                ));
    }

    /**
     * Resolves the notification channel implementation for the given type.
     *
     * @param type notification type
     * @return notification channel service
     * @throws IllegalArgumentException if the type is not supported
     */
    public NotificationChannelService getChannel(NotificationType type) {
        log.debug("Resolving notification channel for type: {}", type);

        NotificationChannelService channel = channels.get(type);

        if(channel == null){
            throw new IllegalArgumentException("Unsupported notification type: " + type);
        }

        return channel;
    }


}
