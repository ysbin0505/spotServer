package com.example.spotserver.config;


import com.example.spotserver.filter.MyFilter1;
import com.example.spotserver.filter.MyFilter2;
import com.example.spotserver.filter.MyFilter3;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    // 시큐리티 필터(필터 체인)가 먼저 다 실행되고 이 필터들이 실행 됌
    // 가장 빨리 실행되고 싶다면 시큐리티 필터에 addFilterBefore를 써주자. (SecurityContextPersistence 앞에하면 가장 빨리 됌)
    @Bean
    public FilterRegistrationBean<MyFilter1> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> filter2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter3> filter3() {
        FilterRegistrationBean<MyFilter3> bean = new FilterRegistrationBean<>(new MyFilter3());
        bean.addUrlPatterns("/*");
        bean.setOrder(2);
        return bean;
    }
}
