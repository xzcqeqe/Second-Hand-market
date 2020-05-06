package com.xzc.seccondhandmarket.dao;

import com.xzc.seccondhandmarket.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

//管理员的Dao层
public interface AdminDao extends JpaRepository<Admin,Integer>, JpaSpecificationExecutor<Admin> {
    @Query(value = "from Admin where a_username= ?1 and  a_password= ?2 ")//验证登录
    Admin findByUsernameAndPassword(String a_username, String a_password);
}
