package com.example.spotserver.domain;

import lombok.Getter;

@Getter
public enum MemberType {

    KAKAO("카카오 로그인 회원");

    private final String message;

    MemberType(String message) {
        this.message = message;
    }
}
