package com.dev.insurance_users.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ErrorMessage {
        private int statusCode;
        private String message;
        private String description;
        private LocalDateTime timestamp;

        public ErrorMessage(int statusCode, String message, String description) {
            this.statusCode = statusCode;
            this.message = message;
            this.description = description;
            this.timestamp = LocalDateTime.now();
        }

        // Getters
        public int getStatusCode() {
            return statusCode;
        }

        public String getMessage() {
            return message;
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}