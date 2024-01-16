package com.example.spotserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/user/**").authenticated() // 인증만 된다면 들어갈 수 있는 주소
                                .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                .anyRequest().permitAll())
                .formLogin(formLogin -> formLogin
                        .usernameParameter("name")
                        .loginPage("/loginForm")
                        .defaultSuccessUrl("/")
                        .loginProcessingUrl("/login") // "/login" 요청시 시큐리티가 낚아채 로그인을 진행해줌.

                        // loginForm에서 login을 왔다면 "/"로 보내고, 다른 페이지에서 로그인 왔다면 그 페이지로 리다이렉트 해줌.
                );

        return http.build();
    }

}
