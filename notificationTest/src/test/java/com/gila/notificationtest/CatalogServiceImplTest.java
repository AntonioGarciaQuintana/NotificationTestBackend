package com.gila.notificationtest;

import com.gila.notificationtest.domain.dto.CategoryDTO;
import com.gila.notificationtest.domain.entity.MessageCategoryEntity;
import com.gila.notificationtest.repository.MessageCategoryRepository;
import com.gila.notificationtest.service.impl.CatalogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceImplTest {

    @Mock
    private MessageCategoryRepository messageCategoryRepository;

    @InjectMocks
    private CatalogServiceImpl catalogService;

    private MessageCategoryEntity sportsCategory;
    private MessageCategoryEntity movieCategory;

    @BeforeEach
    void setUp() {
        sportsCategory = new MessageCategoryEntity();
        sportsCategory.setId((short)1);
        sportsCategory.setCode("SPORTS");
        sportsCategory.setDescription("Sports category");

        movieCategory = new MessageCategoryEntity();
        movieCategory.setId((short)2);
        movieCategory.setCode("MOVIE");
        movieCategory.setDescription("Movie category");
    }

    @Test
    void getCategories_shouldReturnMappedActiveCategories() {
        when(messageCategoryRepository.findByActiveTrue()).thenReturn(List.of(sportsCategory, movieCategory));

        List<CategoryDTO> result = catalogService.getCategories();

        assertNotNull(result);
        assertEquals(2, result.size());

        CategoryDTO first = result.getFirst();
        assertEquals((short) 1, first.id());
        assertEquals("SPORTS", first.code());
        assertEquals("Sports category", first.description());

        CategoryDTO second = result.get(1);
        assertEquals("MOVIE", second.code());
    }

    @Test
    void getCategories_shouldReturnEmptyList_whenNoActiveCategories() {
        when(messageCategoryRepository.findByActiveTrue()).thenReturn(List.of());

        List<CategoryDTO> result = catalogService.getCategories();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
