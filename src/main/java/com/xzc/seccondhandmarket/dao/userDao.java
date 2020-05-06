package com.xzc.seccondhandmarket.dao;

import com.xzc.seccondhandmarket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface userDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    @Query(value = " from User where username = ?1")//注册时跟根据用户名查询
    public User findByUserName(String username);

    @Query(value = "from User where username = ?1 and password =?2")
    public User findByUserNameAndPassword(String username,String password);
}
