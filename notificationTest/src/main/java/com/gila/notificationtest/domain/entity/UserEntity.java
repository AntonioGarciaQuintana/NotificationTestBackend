package com.gila.notificationtest.domain.entity;

import com.gila.notificationtest.domain.enums.MessageCategory;
import com.gila.notificationtest.domain.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    private List<MessageCategory> subscribedCategories;

    private List<NotificationType> channels;
}
