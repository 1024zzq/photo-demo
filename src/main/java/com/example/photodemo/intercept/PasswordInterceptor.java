package com.example.photodemo.intercept;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String password = request.getParameter("pw");
        if (password == null || !password.equals("123")) {
            response.sendRedirect("index.html");
            return false;
        }
        return true;
    }
}