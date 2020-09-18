package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor (loginInterceptor).excludePathPatterns (
                "/index.html",
                "/login",
                "/register.html",
                "/register",
                "/test",
                "/img/*",
                "/img/userPicture/*",
                "/js/*",
                "/uploadUserPicture"
        );
    }
}
