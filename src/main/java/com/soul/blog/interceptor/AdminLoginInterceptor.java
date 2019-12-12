package com.soul.blog.interceptor;

import com.soul.blog.domain.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 符浩灵
 * @date 2019/11/19 21:42
 * 后台登录拦截器
 */
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.判断session中是否有值
        User user = (User) request.getSession().getAttribute("user");
        if(user == null ){// 未登录
            response.sendRedirect("/admin");
            return false;
        }
        // 放行
        return true;
    }
}
