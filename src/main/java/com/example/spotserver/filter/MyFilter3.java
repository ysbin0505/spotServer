package com.example.spotserver.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터3");


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        // 토큰 : good을 id, pw로 정상적인 로그인이 되었다면 토큰을 만들어주고 그걸 응답으로 주면 된다.
        // 요청때마다 토큰이 넘어오면 내가 만든 토큰이 맞는지 검증하면 됌 (RSA, HS256으로 검증)
        if(req.getMethod().equals("POST")) {
            String authorization = req.getHeader("Authorization");
            System.out.println("authorization = " + authorization);

            if(authorization.equals("good")) {
                chain.doFilter(req, res); // 이때 필터 체인을 타게할거고,
            } else {
                res.setCharacterEncoding("UTF-8");
                PrintWriter writer = res.getWriter();
                writer.println("인증 불가");
            }
        }

    }
}
