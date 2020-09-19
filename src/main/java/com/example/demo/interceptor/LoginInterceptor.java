package com.example.demo.interceptor;

import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserInfoService userInfoService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession (false);
        response.setCharacterEncoding ("utf-8");
        if(session!=null && session.getAttribute ("userInfo")!=null){
            if(request.getRequestURI ().equals ("/") || request.getRequestURI ().equals ("/index.html")){
                response.sendRedirect ("/person");
            }
            return true;
        }
        response.sendRedirect ("/index.html");
        return false;
    }
}
