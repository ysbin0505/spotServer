package com.example.spotserver.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    DUPLICATE_LOGINID(HttpStatus.CONFLICT,"중복된 아이디가 존재합니다."),
    DUPLICATE_NAME(HttpStatus.CONFLICT, "중복된 닉네임이 존재합니다."),
    FAIL_LOGIN(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호를 잘못 입력했습니다."),
    NOT_VALID(HttpStatus.BAD_REQUEST, "올바른 입력을 해주세요.");

    private HttpStatus httpStatus;
    private String message;


    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
