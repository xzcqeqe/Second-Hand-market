package com.xzc.seccondhandmarket.service.Impl;

import com.xzc.seccondhandmarket.dao.OrderDao;
import com.xzc.seccondhandmarket.domain.Commodity;
import com.xzc.seccondhandmarket.domain.Order;
import com.xzc.seccondhandmarket.domain.User;
import com.xzc.seccondhandmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    @Transactional
    @Rollback(false)
    public void ConfirmList(Order order, User user) {
        user.getOrderList().add(order);//用户增加历史订单
        order.setUser(user);//订单绑定登录用户
        orderDao.save(order);//订单保存
    }
}
