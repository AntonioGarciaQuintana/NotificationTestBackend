package com.gila.notificationtest.service.impl;

import com.gila.notificationtest.domain.entity.UserEntity;
import com.gila.notificationtest.domain.enums.MessageCategory;
import com.gila.notificationtest.mock.MockUsers;
import com.gila.notificationtest.service.interfaces.UserInterfaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserInterfaceService {

    /**
     * Returns all users subscribed to the given message category.
     *
     * @param category the message category used for filtering
     * @return a list of subscribed users, or an empty list if none are found
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getSubscribedUsers(MessageCategory category) {
        return MockUsers.getUsers().stream()
                .filter(user -> user.getSubscribedCategories().contains(category))
                .toList();
    }
}
