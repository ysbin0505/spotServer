package com.example.spotserver.dto.response;

import com.example.spotserver.domain.Member;
import com.example.spotserver.domain.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private Role role;

    public MemberResponse toDto(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.id = member.getId();
        memberResponse.name = member.getName();
        memberResponse.role = member.getRole();
        return memberResponse;
    }
}
