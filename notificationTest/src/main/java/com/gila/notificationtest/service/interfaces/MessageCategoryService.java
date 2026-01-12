package com.gila.notificationtest.service.interfaces;

import com.gila.notificationtest.domain.entity.MessageCategoryEntity;

public interface MessageCategoryService {
    MessageCategoryEntity getCategoryEntity(String code);
}
