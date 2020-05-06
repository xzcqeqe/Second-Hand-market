package com.xzc.seccondhandmarket.service;

import com.xzc.seccondhandmarket.domain.Commodity;
import com.xzc.seccondhandmarket.domain.pageBean;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CommodityService {
    public List<Commodity> findAll();//查询所有商品
    public  Commodity findById(Integer id);//根据id查找商品
    public void delete(Integer id);//根据id删除商品

    pageBean<Commodity> findByPage(Integer currentPage,Commodity conditionCom);  //按搜索条件查找 并且分页展示

}
