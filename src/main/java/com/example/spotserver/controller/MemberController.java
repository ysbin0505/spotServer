package com.example.spotserver.controller;


import com.example.spotserver.config.jwt.JwtProperties;
import com.example.spotserver.domain.*;
import com.example.spotserver.dto.request.SignInMember;
import com.example.spotserver.dto.request.SignUpMember;
import com.example.spotserver.dto.response.MemberResponse;
import com.example.spotserver.exception.DuplicateException;
import com.example.spotserver.exception.ErrorMsg;
import com.example.spotserver.exception.LoginFailException;
import com.example.spotserver.service.MemberService;
import com.example.spotserver.snsLogin.KakaoApi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/signup")
    public ApiResponse signupMember(@Valid @RequestBody SignUpMember signUpMember) throws DuplicateException {

        ApiResponse apiResponse = new ApiResponse();


        String loginId = signUpMember.getLoginId();
        String name = signUpMember.getName();

        if (memberService.existLoginId(loginId)) {
            throw new DuplicateException(ErrorMsg.DUPLICATE_LOGINID);
        }

        if (memberService.existName(name)) {
            throw new DuplicateException(ErrorMsg.DUPLICATE_NAME);
        }

        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
        Member member = signUpMember.toEntity(signUpMember);
        member.setRole(Role.USER);
        member.setLoginPwd(bCryptPasswordEncoder.encode(member.getLoginPwd()));
        member.setType(MemberType.NORMAL);
        memberService.addMember(member);

        MemberResponse memberResponse = new MemberResponse();
        memberResponse = memberResponse.toDto(member);
        apiResponse.setData(memberResponse);
        return apiResponse;
    }

    @PostMapping("/signin")
    public ApiResponse signinMember(@Valid @RequestBody SignInMember signInMember) throws LoginFailException {

        ApiResponse apiResponse = new ApiResponse();

        String loginId = signInMember.getLoginId();
        String loginPwd = signInMember.getLoginPwd();

        Member findMember = memberService.findByLoginId(loginId);

        if (findMember == null)
            throw new LoginFailException(ErrorMsg.FAIL_LOGIN);
        if (!bCryptPasswordEncoder.matches(loginPwd, findMember.getLoginPwd()))
            throw new LoginFailException(ErrorMsg.FAIL_LOGIN);

        Map<String, Object> tokenInfo = new HashMap<>();
        String token = memberService.createToken(findMember.getId());
        tokenInfo.put("token", token);
        tokenInfo.put("expire_in", JwtProperties.EXPIRE_TIME / 1000);
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
        apiResponse.setData(tokenInfo);
        return apiResponse;
    }

//    @GetMapping("/signup/kakao")
    public String addKakaoMember(@RequestHeader("Authorization") String access_token) {
        Long snsId = KakaoApi.getTokenInfo(access_token);

        if (memberService.existKakaoMember(snsId)) {
            return "이미 존재하는 회원입니다.";
        } else {
            Member member = KakaoApi.getMemberInfo(access_token);
            memberService.addMember(member);
            return "회원가입 완료.";
        }
    }

    @ExceptionHandler(value = DuplicateException.class)
    public ErrorResponse duplicateException(DuplicateException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = LoginFailException.class)
    public ErrorResponse loginFailException(LoginFailException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

}
