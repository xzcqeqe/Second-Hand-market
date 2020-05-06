package com.xzc.seccondhandmarket.service.Impl;

import com.xzc.seccondhandmarket.dao.CommodityDao;
import com.xzc.seccondhandmarket.domain.Commodity;
import com.xzc.seccondhandmarket.domain.pageBean;
import com.xzc.seccondhandmarket.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class CommodityServiceImpl  implements CommodityService {
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private  pageBean<Commodity> compageBean;


    @Override//查询所有商品
    public List<Commodity> findAll() {
        List<Commodity> commodityList = commodityDao.findAll();
        return  commodityList;
    }


    @Override
    @Transactional //根据id查询这个商品 和所属用户
    public Commodity findById(Integer id) {
        Commodity commodity = commodityDao.findById(id).orElse(null);
        return  commodity;
    }

    @Override
    @Transactional//删除商品
    public void delete(Integer id) {
        commodityDao.deleteById(id);
    }


    @Override//完成商品分页查询
    public pageBean<Commodity> findByPage(Integer currentPage,Commodity conditionCom) { //conditionCom为搜索条件
        compageBean.setCurrentPage(currentPage); //设置当前页码
        compageBean.setRows(8);                 //设置每一页条数

        if(currentPage<=1){
            currentPage=1;
        }
        //pageable的接口实现类，从构造方法传入两个参数，第一个参数，当前查询页数，第二个参数 每页查询数量 默认8
        Pageable pageable = PageRequest.of(currentPage-1,8);//当前起始条件，和查询条数

        //封装动态搜索条件
        Specification<Commodity> specification = new Specification<Commodity>() {
            private  Predicate result;
            @Override
            public Predicate toPredicate(Root<Commodity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> name = root.get("name"); //按名字搜索
                Path<Object> category = root.get("category");//按种类搜索
                //构造查询 名字模糊查询 种类精准查询
                Predicate p1 = criteriaBuilder.like(name.as(String.class), "%" + conditionCom.getName() + "%");//模糊
                Predicate p2 = criteriaBuilder.equal(category, conditionCom.getCategory());//精准
                 //将所有查询条件组合 与 关系
                if(conditionCom.getName()!=null&&conditionCom.getCategory()==null){
                    //搜索条件  有名字 没种类
                    return result= criteriaBuilder.and(p1);
                }
                if(conditionCom.getName()==null && conditionCom.getCategory()!=null){
                    //搜索条件  有种类 没名字
                    return result= criteriaBuilder.and(p2);
                }
                if(conditionCom.getName()!=null &&conditionCom.getCategory()!=null){
                    //搜索条件  有名字 有种类
                    return result= criteriaBuilder.and(p1,p2);
                }
                return result;
            }
        };
        Page<Commodity> page = commodityDao.findAll(specification,pageable);     //根据当前页数和默认8条记录 以及条件查询
        compageBean.setTotalCount((int)page.getTotalElements());        //得到总条数
        List<Commodity> pageCom = page.getContent();                //得到数据集合
        compageBean.setList(pageCom);
        compageBean.setTotalPage(page.getTotalPages());            //得到总页数
        return compageBean;         //封装pageBean返回
    }
}
