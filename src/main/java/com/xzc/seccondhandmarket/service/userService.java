package com.xzc.seccondhandmarket.service;

import com.xzc.seccondhandmarket.domain.User;

import java.util.List;
//用户服务层接口
public interface userService {
        boolean register(User user);//处理注册

        User login(User user);//处理登录

        User findById(Integer integer);//根据u_id查询用户

        List<User> findAll();//查询所有
}
