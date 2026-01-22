package com.gila.notificationtest.service.impl;

import com.gila.notificationtest.domain.entity.MessageCategoryEntity;
import com.gila.notificationtest.exception.NotFoundException;
import com.gila.notificationtest.repository.MessageCategoryRepository;
import com.gila.notificationtest.service.interfaces.MessageCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageCategoryServiceImpl implements MessageCategoryService {

    private final MessageCategoryRepository messageCategoryRepository;

    public MessageCategoryServiceImpl(MessageCategoryRepository messageCategoryRepository){
        this.messageCategoryRepository = messageCategoryRepository;
    }

    /**
     * Retrieves a message category entity by its code.
     *
     * @param code message category code
     * @return message category entity
     * @throws IllegalArgumentException if the category does not exist
     */
    @Override
    @Transactional(readOnly = true)
    public MessageCategoryEntity getCategoryEntity(String code) {
        return this.messageCategoryRepository.findByCode(code)
                .orElseThrow(() ->
                new NotFoundException("Category with code " + code + " not found.")
        );
    }
}
