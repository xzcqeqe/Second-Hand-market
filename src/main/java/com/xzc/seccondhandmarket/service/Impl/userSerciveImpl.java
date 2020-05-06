package com.xzc.seccondhandmarket.service.Impl;
import com.xzc.seccondhandmarket.dao.userDao;
import com.xzc.seccondhandmarket.domain.User;
import com.xzc.seccondhandmarket.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userSerciveImpl implements userService {
    @Autowired
    private userDao userDao;

    @Override//处理注册业务
    public boolean register(User user) {
        User result = userDao.findByUserName(user.getUsername());
        if(result==null){//用户名不存在 可以注册
            userDao.save(user);
            return  true;
        }//用户名存在，注册失败 换一个用户名试试
        return false;
    }
    @Override//处理登录业务
    public  User login(User user){
        User result = userDao.findByUserNameAndPassword(user.getUsername(), user.getPassword());
        return result;
    }

    @Override
    public User findById(Integer integer) {//根据u_id查询用户
        User user = userDao.findById(integer).orElse(null);
        return user;
    }

    @Override
    public List<User> findAll() {//查询所有用户
        List<User> userList = userDao.findAll();
        return userList;
    }
}
