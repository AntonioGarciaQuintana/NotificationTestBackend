package com.gila.notificationtest.domain.dto;

import com.gila.notificationtest.domain.enums.MessageCategory;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

    @NotNull(message = "Category is required.")
    private MessageCategory category;

    @NotBlank(message = "The message cannot be empty.")
    @Size(max = 500)
    @Column(length = 500)
    private String message;

}
