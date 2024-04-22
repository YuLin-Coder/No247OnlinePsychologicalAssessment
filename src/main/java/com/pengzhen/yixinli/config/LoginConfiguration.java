package com.pengzhen.yixinli.config;

import com.pengzhen.yixinli.controller.login.CheckLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

    //注册一个拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CheckLoginInterceptor interceptor = new CheckLoginInterceptor();
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(interceptor);

        //拦截路径
        interceptorRegistration.addPathPatterns("/**");
        // 排除路径
        interceptorRegistration.excludePathPatterns("/defaultKaptcha/**");
        interceptorRegistration.excludePathPatterns("/login");
        interceptorRegistration.excludePathPatterns("/");
        interceptorRegistration.excludePathPatterns("/article/list");
        interceptorRegistration.excludePathPatterns("/article/get");
        interceptorRegistration.excludePathPatterns("/leacotsUi/**");
        interceptorRegistration.excludePathPatterns("/leacots/save");
        interceptorRegistration.excludePathPatterns("/noticeClientUi/list");
        interceptorRegistration.excludePathPatterns("/noticeClientUi/get");
        interceptorRegistration.excludePathPatterns("/login_do");
        interceptorRegistration.excludePathPatterns("/loginout");
        // 排除资源请求
        interceptorRegistration.excludePathPatterns("/layuiadmin/**");
        interceptorRegistration.excludePathPatterns("/res/**");
    }
}
