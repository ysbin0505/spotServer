package com.example.spotserver.controller;


import com.example.spotserver.config.jwt.JwtProperties;
import com.example.spotserver.domain.*;
import com.example.spotserver.dto.request.SignInMember;
import com.example.spotserver.dto.request.SignUpMember;
import com.example.spotserver.dto.response.MemberResponse;
import com.example.spotserver.exception.DuplicateException;
import com.example.spotserver.exception.ErrorCode;
import com.example.spotserver.exception.LoginFailException;
import com.example.spotserver.service.MemberService;
import com.example.spotserver.snsLogin.KakaoApi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MemberResponse> signupMember(@Valid @RequestBody SignUpMember signUpMember) throws DuplicateException {

        Member member = memberService.addMember(signUpMember);

        MemberResponse memberResponse = new MemberResponse();
        memberResponse = memberResponse.toDto(member);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<Map> signinMember(@Valid @RequestBody SignInMember signInMember) throws LoginFailException {

        ApiResponse apiResponse = new ApiResponse();

        String loginId = signInMember.getLoginId();
        String loginPwd = signInMember.getLoginPwd();

        Member findMember = memberService.findByLoginId(loginId);

        if (findMember == null)
            throw new LoginFailException(ErrorCode.FAIL_LOGIN);
        if (!bCryptPasswordEncoder.matches(loginPwd, findMember.getLoginPwd()))
            throw new LoginFailException(ErrorCode.FAIL_LOGIN);

        Map<String, Object> tokenInfo = new HashMap<>();
        String token = memberService.createToken(findMember.getId());
        tokenInfo.put("token", token);
        tokenInfo.put("expire_in", JwtProperties.EXPIRE_TIME / 1000);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tokenInfo);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long memberId) {
        Member member = memberService.getMember(memberId);

        MemberResponse memberResponse = MemberResponse.toDto(member);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberResponse);
    }

    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<ErrorResponse> duplicateException(DuplicateException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(value = LoginFailException.class)
    public ResponseEntity<ErrorResponse> loginFailException(LoginFailException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

}
