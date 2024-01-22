package com.example.spotserver.exception;

import lombok.Getter;

@Getter
public class LoginFailException extends Exception{

    private ErrorCode errorCode;

    public LoginFailException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
