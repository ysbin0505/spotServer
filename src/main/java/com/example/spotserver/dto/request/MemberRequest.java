package com.example.spotserver.dto.request;

import com.example.spotserver.domain.Member;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class MemberRequest {

    private String loginId;
    private String loginPwd;
    private String name;


    public Member toEntity(MemberRequest memberRequest) {
        Member member = new Member();
        member.setLoginId(memberRequest.loginId);
        member.setLoginPwd(memberRequest.loginPwd);
        member.setName(memberRequest.name);
        return member;
    }
}
