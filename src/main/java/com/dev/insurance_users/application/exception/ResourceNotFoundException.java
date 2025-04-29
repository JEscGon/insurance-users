package com.dev.insurance_users.application.exception;

import com.dev.insurance_users.infrastructure.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        GlobalExceptionHandler.ErrorMessage message = new GlobalExceptionHandler.ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
