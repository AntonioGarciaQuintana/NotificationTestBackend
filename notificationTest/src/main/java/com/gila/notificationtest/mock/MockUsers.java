package com.gila.notificationtest.mock;

import com.gila.notificationtest.domain.enums.MessageCategory;
import com.gila.notificationtest.domain.enums.NotificationType;
import com.gila.notificationtest.domain.entity.UserEntity;

import java.util.List;

public class MockUsers {
    public static List<UserEntity> getUsers() {
        return List.of(
                UserEntity.builder()
                        .id(1L)
                        .name("Alice")
                        .email("alice@example.com")
                        .phoneNumber("1234567890")
                        .subscribedCategories(List.of(MessageCategory.FINANCE))
                        .channels(List.of(NotificationType.EMAIL, NotificationType.SMS))
                        .build(),

                UserEntity.builder()
                        .id(2L)
                        .name("Bob")
                        .email("bob@example.com")
                        .phoneNumber("0987654321")
                        .subscribedCategories(List.of(MessageCategory.MOVIES))
                        .channels(List.of(NotificationType.PUSH, NotificationType.SMS))
                        .build(),

                UserEntity.builder()
                        .id(2L)
                        .name("Bob")
                        .email("bob@example.com")
                        .phoneNumber("0987654321")
                        .subscribedCategories(List.of(MessageCategory.FINANCE))
                        .channels(List.of(NotificationType.PUSH))
                        .build()
        );
    }
}
