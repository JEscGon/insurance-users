package com.dev.insurance_users.application.exception;

import com.dev.insurance_users.infrastructure.exception.PartSaveErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PartSaveErrorException extends RuntimeException {

    private String message;

    private PartSaveErrorType errorType;

}

