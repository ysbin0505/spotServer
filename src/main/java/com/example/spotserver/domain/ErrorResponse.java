package com.example.spotserver.domain;

import lombok.Data;

@Data
public class ErrorResponse {

    private int errorCode;
    private String message;

}
