package com.gila.notificationtest.service.impl;

import com.gila.notificationtest.domain.dto.CategoryDTO;
import com.gila.notificationtest.domain.entity.MessageCategoryEntity;
import com.gila.notificationtest.repository.MessageCategoryRepository;
import com.gila.notificationtest.service.interfaces.CatalogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final MessageCategoryRepository messageCategoryRepository;

    public CatalogServiceImpl(MessageCategoryRepository messageCategoryRepository){
        this.messageCategoryRepository = messageCategoryRepository;
    }

    /**
     * Retrieves all active message categories.
     *
     * @return list of active category DTOs
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategories() {
        return this.messageCategoryRepository.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    /**
     * Maps a message category entity to a DTO.
     *
     * @param entity message category entity
     * @return mapped category DTO
     */
    private CategoryDTO toDTO(MessageCategoryEntity entity) {
        return new CategoryDTO(
                entity.getId(),
                entity.getCode(),
                entity.getDescription()
        );
    }
}
