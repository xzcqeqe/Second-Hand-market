package com.xzc.seccondhandmarket.service.Impl;

import com.xzc.seccondhandmarket.dao.AdminDao;
import com.xzc.seccondhandmarket.domain.Admin;
import com.xzc.seccondhandmarket.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;

@Service//管理员的服务层
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override//验证管理员登录
    public Admin login(Admin admin) {
        String a_username = admin.getA_username();
        String a_password = admin.getA_password();
       return adminDao.findByUsernameAndPassword(a_username,a_password);
    }
}
