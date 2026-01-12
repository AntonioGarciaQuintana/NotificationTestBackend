package com.gila.notificationtest.controller;


import com.gila.notificationtest.domain.dto.CategoryDTO;
import com.gila.notificationtest.domain.dto.SentNotificationDTO;
import com.gila.notificationtest.service.interfaces.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogs")
@Slf4j
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService){
        this.catalogService = catalogService;
    }

    /**
     * Retrieves all available message categories.
     *
     * @return list of message category DTOs
     */
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllNotifications(){
        log.info("Getting all notifications");
        return ResponseEntity.ok(this.catalogService.getCategories());
    }
}
