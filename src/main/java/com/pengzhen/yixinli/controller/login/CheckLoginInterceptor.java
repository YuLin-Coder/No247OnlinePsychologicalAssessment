package com.pengzhen.yixinli.controller.login;

import com.pengzhen.yixinli.common.LoginSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类描述信息 登入拦截器
 *
 * @author Tomlin
 * @ClassName CheckLoginInterceptor
 * @Description: TODO
 * @date 2018/12/27 15:59
 * @Viersion V1.0.1
 */

public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登入
        if (LoginSession.getCurrentUser() == null) {
            response.sendRedirect("/login");
            System.out.println("CheckLoginInterceptor.preHandle" + "----------------未登入------拦截请求--------------->");
            return false;
        }
        return true;
    }
}
