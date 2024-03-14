package com.restaurant.restaurantorderingapp.exceptions.exceptionHandlers;

import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyResultException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException e) {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        Map<String, String> errorResponse = new LinkedHashMap<>();
        errorResponse.put("status code", String.valueOf(statusCode.value()));
        errorResponse.put("message", e.getMessage());
        log.error("NotFoundException", errorResponse);
        return ResponseEntity
                .status(statusCode)
                .body(errorResponse);
    }

    @ExceptionHandler(EmptyResultException.class)
    public ResponseEntity<Map<String, String>> handleEmptyResultException(EmptyResultException e) {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        Map<String, String> errorResponse = new LinkedHashMap<>();
        errorResponse.put("status code", String.valueOf(statusCode.value()));
        errorResponse.put("message", e.getMessage());
        log.error("NotFoundException", errorResponse);
        return ResponseEntity
                .status(statusCode)
                .body(errorResponse);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateKeyException(DuplicateKeyException e) {
        HttpStatus statusCode = HttpStatus.CONFLICT;
        Map<String, String> errorResponse = new LinkedHashMap<>();
        errorResponse.put("status code", String.valueOf(statusCode.value()));
        errorResponse.put("message", e.getMessage());
        log.error("DuplicateKeyException", errorResponse);

        return ResponseEntity
                .status(statusCode)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        Map<String, String> errorResponse = new LinkedHashMap<>();
        errorResponse.put("status code", String.valueOf(statusCode.value()));
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorResponse.put(fieldName, errorMessage);
        });
        log.error("MethodArgumentNotValidException", errorResponse);
        return ResponseEntity
                .status(statusCode)
                .body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        Map<String, String> errorResponse = new LinkedHashMap<>();
        errorResponse.put("status code", String.valueOf(statusCode.value()));
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errorResponse.put(fieldName, errorMessage);
        });
        log.error("ConstraintViolationException", errorResponse);
        return ResponseEntity
                .status(statusCode)
                .body(errorResponse);
    }
}