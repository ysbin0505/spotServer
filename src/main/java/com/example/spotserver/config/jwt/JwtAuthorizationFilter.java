package com.example.spotserver.config.jwt;

// 시큐리티 필터중 BasicAuthenticationFilter가 있다.
// 권한 또는 인증이 필요한 요청을 했을때 이 필터를 탄다.
// 권한 또는 인증이 필요없다면 안탄다.

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.spotserver.config.auth.PrincipalDetails;
import com.example.spotserver.domain.Member;
import com.example.spotserver.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private MemberRepository memberRepository;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader("Authorization");

        // header가 있는지 확인
        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            System.out.println("header 미통과");
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "");
        Long id = JWT.require(Algorithm.HMAC256(JwtProperties.SECRET_KEY))
                .build()
                .verify(jwtToken)
                .getClaim("id")
                .asLong();
        System.out.println("서명 실행");

        // 서명이 정상적으로 됨
        if(id != null) {
            System.out.println("서명 통과");
            Member member = memberRepository.findById(id).get();
            System.out.println("member = " + member);

            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 세션에 강제 등록
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }


    }
}
