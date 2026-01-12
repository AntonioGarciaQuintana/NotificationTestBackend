package com.gila.notificationtest.service.interfaces;

import com.gila.notificationtest.domain.dto.CategoryDTO;

import java.util.List;

public interface CatalogService {
    List<CategoryDTO> getCategories();
}
