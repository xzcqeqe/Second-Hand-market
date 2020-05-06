package com.xzc.seccondhandmarket.service;

import com.xzc.seccondhandmarket.domain.Commodity;
import com.xzc.seccondhandmarket.domain.Order;
import com.xzc.seccondhandmarket.domain.User;

//订单接口
public interface OrderService {
    void ConfirmList(Order oder, User user);
}
