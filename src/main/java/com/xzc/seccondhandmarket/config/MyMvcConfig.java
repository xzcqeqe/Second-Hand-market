package com.xzc.seccondhandmarket.config;

import com.xzc.seccondhandmarket.Inceptor.AdminLoginHanderInceptor;
import com.xzc.seccondhandmarket.Inceptor.LoginHandlerInceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public  class  MyMvcConfig implements WebMvcConfigurer{

    @Override//普通页面跳转
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("register");//默认用户登录首页
        registry.addViewController("/user/login.html").setViewName("login");//用户登录请求转登录
        registry.addViewController("/user/register.html").setViewName("register");//用户注册请求转注册首页
        registry.addViewController("/register_ok.html").setViewName("register_ok");//用户注册成功转ok提示
        registry.addViewController("/admin.html").setViewName("adminLogin");//管理员登录请求转管理员登录
    }

   //注册拦截器   暂时放弃做这个
//    public void addInterceptors(InterceptorRegistry registry) {
//        //如果用户没有没登陆，拦截所有和用户操作有关的操作
//        registry.addInterceptor(new LoginHandlerInceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/","/user/login.html","user/register.html",
//                        "/commodity/login","/admin/**","/admin.html"
//                ,"/css/**","/js/**","/images/**","/webjars/**","/asserts/**");//去除掉管理员拦截和一些静态资源

        //如果管理员没有登录，拦截和管理员有关操作
//        registry.addInterceptor(new AdminLoginHanderInceptor()).addPathPatterns("/admin/**")
//                .excludePathPatterns("/admin.html","/admin/login","/admin/login.html","/asserts/**","/admin"
//                        ,"/css/**","/js/**","/images/**","/webjars/**");//去除掉一些静态资源
//    }

}

