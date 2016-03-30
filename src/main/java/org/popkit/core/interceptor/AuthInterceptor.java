package org.popkit.core.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理权限问题
 * Author: Aborn Jiang
 * Email : aborn.jiang@gmail.com
 * Date  : 03-17-2016
 * Time  : 11:07 AM
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        } else {
            return true;
        }
    }
}
