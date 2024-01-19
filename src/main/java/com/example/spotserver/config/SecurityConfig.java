package com.example.spotserver.config;

import com.example.spotserver.config.jwt.JwtAuthenticationFilter;
import com.example.spotserver.config.jwt.JwtAuthorizationFilter;
import com.example.spotserver.filter.MyFilter3;
import com.example.spotserver.repository.MemberRepository;
import com.example.spotserver.securityStudy.TestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CorsConfig corsConfig;
    private MemberRepository memberRepository;


    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public SecurityConfig(CorsConfig corsConfig, MemberRepository memberRepository) {
        this.corsConfig = corsConfig;
        this.memberRepository = memberRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session ->
                session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 생성 X

        http.authorizeHttpRequests(request ->
                request
                        .requestMatchers("/user/**").authenticated() // 인증만 된다면 들어갈 수 있는 주소
                        .requestMatchers("/manager/**").hasAnyAuthority("ADMIN", "MANAGER")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll());

        http.formLogin(formLogin ->
                formLogin
                        .disable()); // 폼 태그 로그인 안쓰겠다.

        http.httpBasic(httpBasic ->
                httpBasic
                        .disable()); // 기본적인 HTTP 로그인 안쓰겠다. (ID, PW를 항상 포함하여 요청함)

        http.apply(new MyCustomDsl());

        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {

            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository));

        }
    }

}
