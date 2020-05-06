package com.xzc.seccondhandmarket.Inceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*  管理员登录拦截
***/
public class AdminLoginHanderInceptor implements HandlerInterceptor {
    @Override//执行方法前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginAdmin = request.getSession().getAttribute("login_admin");
        if(loginAdmin==null){//未登录，返回登陆界面
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/admin/login.html").forward(request,response);
            return false;
        }else {//已经登录 放行
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
