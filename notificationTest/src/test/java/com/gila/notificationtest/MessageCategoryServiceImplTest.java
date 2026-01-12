package com.gila.notificationtest;

import com.gila.notificationtest.domain.entity.MessageCategoryEntity;
import com.gila.notificationtest.repository.MessageCategoryRepository;
import com.gila.notificationtest.service.impl.MessageCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageCategoryServiceImplTest {

    @Mock
    private MessageCategoryRepository repository;

    @InjectMocks
    private MessageCategoryServiceImpl service;

    private MessageCategoryEntity emailCategory;
    private MessageCategoryEntity smsCategory;

    @BeforeEach
    void setUpModels() {
        emailCategory = new MessageCategoryEntity();
        emailCategory.setId((short) 1);
        emailCategory.setCode("EMAIL");
        emailCategory.setDescription("Email notifications");
        emailCategory.setActive(true);

        smsCategory = new MessageCategoryEntity();
        smsCategory.setId((short) 2);
        smsCategory.setCode("SMS");
        smsCategory.setDescription("SMS notifications");
        smsCategory.setActive(true);
    }

    @Test
    void getCategoryEntity_shouldReturnEmailCategory_whenExists() {
        when(repository.findByCode("EMAIL")).thenReturn(Optional.of(emailCategory));

        MessageCategoryEntity result = service.getCategoryEntity("EMAIL");

        assertNotNull(result);
        assertEquals("EMAIL", result.getCode());
        verify(repository, times(1)).findByCode("EMAIL");
    }

    @Test
    void getCategoryEntity_shouldThrowException_whenCategoryDoesNotExist() {
        when(repository.findByCode("INVALID")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.getCategoryEntity("INVALID")
        );
        assertEquals("Invalid message category", exception.getMessage());
        verify(repository, times(1)).findByCode("INVALID");
    }
}
