package com.gila.notificationtest.service.interfaces;

import com.gila.notificationtest.domain.entity.UserEntity;
import com.gila.notificationtest.domain.enums.MessageCategory;

import java.util.List;

public interface UserInterfaceService {
    List<UserEntity> getSubscribedUsers(MessageCategory categoryCode);
}
