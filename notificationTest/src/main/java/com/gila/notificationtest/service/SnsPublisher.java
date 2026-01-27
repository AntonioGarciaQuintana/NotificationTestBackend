package com.gila.notificationtest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
@Slf4j
public class SnsPublisher {

    private final SnsClient snsClient;

    SnsPublisher(SnsClient  snsClient){
        this.snsClient = snsClient ;
    }

    public void publish(String topicArn, String subject, String message) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .subject(subject)
                .message(message)
                .build();

        snsClient.publish(request);

        log.info("Message published to SNS topic {}", topicArn);
    }
}
