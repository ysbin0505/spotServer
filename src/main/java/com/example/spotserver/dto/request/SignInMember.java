package com.example.spotserver.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class SignInMember {

    @NotEmpty(message = "아이디를 비울 수 없습니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 비울 수 없습니다.")
    private String loginPwd;
}
