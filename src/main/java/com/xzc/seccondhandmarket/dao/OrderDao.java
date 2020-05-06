package com.xzc.seccondhandmarket.dao;

import com.xzc.seccondhandmarket.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDao extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {
}
