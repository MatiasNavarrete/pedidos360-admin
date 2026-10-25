package com.example.admin_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException ex) {
        HttpStatus status = ex.getMessage().contains("ya existe")
                ? HttpStatus.CONFLICT : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(ex.getMessage());
    }
}
