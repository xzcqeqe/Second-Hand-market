package com.xzc.seccondhandmarket.dao;

import com.xzc.seccondhandmarket.domain.WaitCommodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//等待审核的商品dao
public interface WaitCommodityDao extends JpaRepository<WaitCommodity,Integer>, JpaSpecificationExecutor<WaitCommodity> {
}
