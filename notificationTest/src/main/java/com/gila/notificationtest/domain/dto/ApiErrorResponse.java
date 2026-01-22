package com.gila.notificationtest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    private String code;
    private String message;
    private List<FieldErrorDTO> errors;
    private Instant timestamp;
    private String path;
}
