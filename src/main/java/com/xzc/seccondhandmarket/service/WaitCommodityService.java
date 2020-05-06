package com.xzc.seccondhandmarket.service;

import com.xzc.seccondhandmarket.domain.User;
import com.xzc.seccondhandmarket.domain.WaitCommodity;

import java.util.List;

public interface WaitCommodityService {

    void save(WaitCommodity waitcom, User login_user);//保存待处理订单

    List<WaitCommodity> findAll();//查询所有待处理订单

    WaitCommodity findById(Integer id);

    void pass(WaitCommodity waitCommodity);//该商品通过审核

    void refuse(WaitCommodity waitCommodity);//拒绝该商品通过
}
