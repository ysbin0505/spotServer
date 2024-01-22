package com.example.spotserver.domain;

import lombok.Data;

@Data
public class ErrorResponse {

    private String errorCode;
    private String message;

}
