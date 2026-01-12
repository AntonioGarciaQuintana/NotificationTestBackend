package com.gila.notificationtest;

import com.gila.notificationtest.domain.entity.UserEntity;
import com.gila.notificationtest.domain.enums.MessageCategory;
import com.gila.notificationtest.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl();
    }

    @Test
    void getSubscribedUsers_shouldReturnOnlyUsersSubscribedToCategory() {

        MessageCategory category = MessageCategory.FINANCE;

        List<UserEntity> result = service.getSubscribedUsers(category);

        assertNotNull(result);
        assertTrue(result.size() > 0, "At least one user must return");
        for (UserEntity user : result) {
            assertTrue(user.getSubscribedCategories().contains(category),
                    "User " + user.getName() + " is not subscribed to " + category);
        }
    }

    @Test
    void getSubscribedUsers_shouldReturnEmptyList_whenNoUserSubscribed() {
        MessageCategory category = MessageCategory.SPORTS;

        List<UserEntity> result = service.getSubscribedUsers(category);

        assertNotNull(result);
        assertTrue(result.isEmpty(), "The list should be empty if no one is subscribed.");
    }
}
