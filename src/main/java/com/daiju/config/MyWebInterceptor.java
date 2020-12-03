package com.daiju.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author WDY
 * @Date 2020-11-15 16:24
 * @Description TODO
 */
public class MyWebInterceptor implements HandlerInterceptor {

    public static final String ROOT_PATH = "/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        if (user == null) {
            String requestUri = request.getRequestURI();
            if (!ROOT_PATH .equals(requestUri)) {
                session.setAttribute("redirect",requestUri);
            }
            request.getRequestDispatcher("/index").forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){

    }
}
