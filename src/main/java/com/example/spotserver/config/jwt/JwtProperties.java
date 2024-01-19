package com.example.spotserver.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class JwtProperties {

    public static String SECRET_KEY;
    public static int EXPIRE_TIME = 1500000;
    public static String TOKEN_PREFIX = "Bearer ";
    public static String HEADER_STRING = "Authorization";


    @Value("${jwt.secrectKey}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }
}
