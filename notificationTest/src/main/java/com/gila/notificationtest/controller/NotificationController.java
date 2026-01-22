package com.gila.notificationtest.controller;

import com.gila.notificationtest.domain.dto.NotificationRequest;
import com.gila.notificationtest.domain.dto.ApiResponse;
import com.gila.notificationtest.domain.dto.SentNotificationDTO;
import com.gila.notificationtest.service.impl.NotificationService;
import com.gila.notificationtest.service.interfaces.SentNotificationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;
    private final SentNotificationService sentNotificationService;

    public NotificationController(NotificationService notificationService,
                                  SentNotificationService sentNotificationService){
        this.notificationService = notificationService;
        this.sentNotificationService = sentNotificationService;
    }

    /***
     *
     * @param request JSON with category y message
     * @return ResponseEntity with shipping information
     */
    @PostMapping("/send")
    public ResponseEntity<ApiResponse> send(@Valid @RequestBody NotificationRequest request){
        log.info("Receiving request to send notification to category '{}' with message: '{}'",
                request.getCategory(), request.getMessage());

        int totalNotifications = notificationService.send(request);

        ApiResponse response = new ApiResponse(
                request.getCategory(),
                request.getMessage(),
                totalNotifications,
                "SUCCESS"
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all sent notifications ordered from newest to oldest.
     *
     * @return list of sent notification records
     */
    @GetMapping()
    public ResponseEntity<List<SentNotificationDTO>> getAllNotifications(){
        log.info("Getting all notifications");
        return ResponseEntity.ok(this.sentNotificationService.getAllSentNotifications());
    }
}
