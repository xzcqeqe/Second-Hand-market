package com.xzc.seccondhandmarket.service;

import com.xzc.seccondhandmarket.dao.AdminDao;
import com.xzc.seccondhandmarket.domain.Admin;

public interface AdminService {
    Admin login(Admin admin);//管理员的登录验证
}
