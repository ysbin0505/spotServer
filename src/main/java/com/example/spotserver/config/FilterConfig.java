package com.example.spotserver.config;


import com.example.spotserver.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    // 시큐리티 필터(필터 체인)가 먼저 다 실행되고 이 필터들이 실행 됌
    // 가장 빨리 실행되고 싶다면 시큐리티 필터에 addFilterBefore를 써주자. (SecurityContextPersistence 앞에하면 가장 빨리 됌)
    @Bean
    public FilterRegistrationBean<MyFilter> filter1() {
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>(new MyFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
}
