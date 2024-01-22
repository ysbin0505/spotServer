package com.example.spotserver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    public static final String SUCCESS_STATUS = "success";

    private String status;
    private T data;

}
