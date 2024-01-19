package com.example.spotserver.domain;

import lombok.Getter;

@Getter
public enum Role {

    USER("일반 유저"),
    ADMIN("관리자");

    private String message;

    Role(String message) {
        this.message = message;
    }
}
