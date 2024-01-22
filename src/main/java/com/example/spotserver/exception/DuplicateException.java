package com.example.spotserver.exception;

import lombok.Getter;

@Getter
public class DuplicateException extends Exception {

    private ErrorCode errorCode;

    public DuplicateException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
