package com.example.spotserver.controller;


import com.example.spotserver.domain.Member;
import com.example.spotserver.service.MemberService;
import com.example.spotserver.snsLogin.KakaoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signup/kakao")
    public String addKakaoMember(@RequestHeader("Authorization") String access_token) {
        Long snsId = KakaoApi.getTokenInfo(access_token);

        if(memberService.existKakaoMember(snsId)) {
            return "이미 존재하는 회원입니다.";
        } else {
            Member member = KakaoApi.getMemberInfo(access_token);
            memberService.addMember(member);
            return "회원가입 완료.";
        }
    }

}
