package com.ecolife.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object currentUser = session.getAttribute("currentUser");
        if (Objects.isNull(currentUser)) {
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return true;
    }
}