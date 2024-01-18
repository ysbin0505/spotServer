package com.example.spotserver.config.jwt;

import com.example.spotserver.config.auth.PrincipalDetails;
import com.example.spotserver.securityStudy.TestUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 필터가 있는데
// "/login" 요청해서 유저네임, 패스워드를 post 요청하면 이 필터가 동작함.
// 현재 formLogin을 꺼놨기에 작동 안함.
// 이 필터를 다시 시큐리티 Config에 등록해주면 된다.
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    // "/login" 요청시 로그인 시도를 위해 동작하는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("로그인 시도중");

        // username, pwd를 받아서 authenticationManager로 로그인 시도를 하면 PrincipalDetailsService가 호출 loadUserByUsername 함수 실
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            TestUser testUser = objectMapper.readValue(request.getInputStream(), TestUser.class);
            System.out.println("testUser = " + testUser);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(testUser.getName(), testUser.getPassword());
            //PrincipalDetailsService의 loadUserByUsername()가 실행
            //DB에 있는 name과 pwd가 일치하면 authentication이 리턴됌.
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);
            System.out.println("로그인 성공. authentication = " + authentication);
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("principalDetails = " + principalDetails.getTestUser());

            // authentication이 세션에 저장됌, 세션 굳이? 권한 관리를 시큐리티가 대신 해주기때문에 편하려고 세션 저장함.
            // JWT 토큰 사용하며 세션을 만들 이유는 없지만 권한처리때문에 session에 넣음
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    // attemptAuthentication 성공시 -> successfulAuth 실행 됌. 여기서 JWT 만들어서 응답하자.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("로그인 성공");
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("로그인 실패");
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
