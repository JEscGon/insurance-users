package com.dev.insurance_users.infrastructure.exception;

import com.dev.insurance_users.application.exception.DuplicateResourceException;
import com.dev.insurance_users.application.exception.PartSaveErrorException;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(Exception ex, WebRequest request) {
        log.error("Stack trace", ex);
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorMessage> duplicateResourceException(DuplicateResourceException ex, WebRequest request) {
        log.error("Stack trace", ex);
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        log.error("Stack trace", ex);
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(PartSaveErrorException.class)
    public ResponseEntity<ErrorMessage> partSaveError(PartSaveErrorException ex, WebRequest request) {
        log.error("Stack trace", ex);

        if (ex.getErrorType().equals(PartSaveErrorType.EXISTING_USER)) {
            return new ResponseEntity<>(new ErrorMessage(
                    HttpStatus.CONFLICT.value(),
                    ex.getMessage(),
                    request.getDescription(false)
            ), HttpStatus.CONFLICT);
        } else if (ex.getErrorType().equals(PartSaveErrorType.EXISTING_VEHICLE)) {
            return new ResponseEntity<>(new ErrorMessage(
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    ex.getMessage(),
                    request.getDescription(false)
            ), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false)
        ), HttpStatus.INTERNAL_SERVER_ERROR);
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


