package com.gila.notificationtest.exception;

import com.gila.notificationtest.domain.dto.ApiErrorResponse;
import com.gila.notificationtest.domain.dto.FieldErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request){

        List<FieldErrorDTO> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> new FieldErrorDTO(e.getField(), e.getDefaultMessage()))
                .toList();

        return new ApiErrorResponse(
                "VALIDATION_ERROR",
                "One or more fields are invalid",
                errors,
                Instant.now(),
                request.getRequestURI()
        );

    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleBusiness(BusinessException ex,
                                           HttpServletRequest request) {

        return new ApiErrorResponse(
                ex.getCode(),
                ex.getMessage(),
                null,
                Instant.now(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleGeneric(Exception ex,
                                          HttpServletRequest request) {

        return new ApiErrorResponse(
                "INTERNAL_ERROR",
                "An unexpected error occurred. Please try again later.",
                null,
                Instant.now(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ApiErrorResponse handleDb(DataAccessException ex,
                                     HttpServletRequest request) {

        return new ApiErrorResponse(
                "DATABASE_UNAVAILABLE",
                "Service temporarily unavailable",
                null,
                Instant.now(),
                request.getRequestURI()
        );
    }
}
