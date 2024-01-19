package com.example.spotserver.controller;


import com.example.spotserver.domain.Member;
import com.example.spotserver.domain.MemberType;
import com.example.spotserver.domain.Role;
import com.example.spotserver.service.MemberService;
import com.example.spotserver.snsLogin.KakaoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public MemberController(MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    public String addMember(@RequestBody Member member) {

        String loginId = member.getLoginId();
        String name = member.getName();

        if(loginId == null || memberService.existLoginId(loginId)) {
            return "아이디 중복 또는 빈칸";
        }
        if(name == null || memberService.existName(name)) {
            return "닉네임 중복 또는 빈칸";
        }

        member.setRole(Role.USER);
        member.setLoginPwd(bCryptPasswordEncoder.encode(member.getLoginPwd()));
        member.setType(MemberType.NORMAL);
        memberService.addMember(member);
        return "ok";
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
