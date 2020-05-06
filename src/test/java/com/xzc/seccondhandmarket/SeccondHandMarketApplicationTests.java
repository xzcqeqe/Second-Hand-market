package com.xzc.seccondhandmarket;

import com.xzc.seccondhandmarket.dao.CommodityDao;
import com.xzc.seccondhandmarket.dao.OrderDao;
import com.xzc.seccondhandmarket.dao.userDao;
import com.xzc.seccondhandmarket.domain.Commodity;
import com.xzc.seccondhandmarket.domain.Order;
import com.xzc.seccondhandmarket.domain.User;
import com.xzc.seccondhandmarket.domain.pageBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SeccondHandMarketApplicationTests {
    @Autowired
    private userDao userDao;
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private  pageBean<Commodity> compageBean;


    @Test
    void contextLoads() {
        Commodity commodity = commodityDao.findById(2).orElse(null);
        System.out.println(commodity);
        User user = commodity.getUser();
        System.out.println(user);
    }
    @Test
    @Transactional
    @Rollback(false)
    public  void  testSave(){
        User user = new User();
        user.setUsername("测试测试11");
        Commodity commodity = new Commodity();
        commodity.setName("不作死不会死系列");

        commodity.setUser(user);
        user.getCommodityList().add(commodity);

        System.out.println(user);
        System.out.println(commodity);
        userDao.save(user);
        commodityDao.save(commodity);
    }
    @Transactional
    @Test
    public void findTest(){
        Commodity commodityById =commodityDao.findById(1).orElse(null);
        User user = commodityById.getUser();
        System.out.println(user);
        System.out.println(commodityById);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void saveTest(){
        User user = userDao.findById(2).orElse(null);
        Order order = new Order();
        order.setO_seller("卖家");
        order.setO_buyer("买家");
        order.setUser(user);
        user.getOrderList().add(order);
        orderDao.save(order);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void fTest(){
        Order order = orderDao.findById(4).orElse(null);
        User user = order.getUser();
        System.out.println(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void  delete(){
        orderDao.deleteById(9);
    }

    @Test
    public void findByPage() {
        //pageable的接口实现类，从构造方法传入两个参数，第一个参数，当前查询页数，第二个参数 每页查询数量 默认8

        Pageable pageable = PageRequest.of(0,8);
        Page<Commodity> page = commodityDao.findAll(pageable);     //根据当前页数和默认8条记录查询

        compageBean.setTotalCount((int)page.getTotalElements());        //得到总条数
        List<Commodity> pageCom = page.getContent();                //得到数据集合
        compageBean.setList(pageCom);
        compageBean.setCurrentPage(1);
        compageBean.setRows(8);
        compageBean.setTotalPage(page.getTotalPages());            //得到总页数
              //封装pageBean返回
        System.out.println(page);
        System.out.println("=======================");
        System.out.println(compageBean.getList());
    }

    @Test
    @Transactional
    public  void findByCondition(){
        Commodity conditionCom =new Commodity();
//        conditionCom.setName("苹");
//        conditionCom.setCategory("手机");
        Integer currentPage = 1;
        compageBean.setCurrentPage(currentPage); //设置当前页码
        compageBean.setRows(8);                 //设置每一页条数

        //pageable的接口实现类，从构造方法传入两个参数，第一个参数，当前查询页数，第二个参数 每页查询数量 默认8
        Pageable pageable = PageRequest.of(currentPage-1,8);//当前起始条件，和查询条数

        //封装动态搜索条件
        Specification<Commodity> specification = new Specification<Commodity>() {
            Predicate result;
            @Override
            public Predicate toPredicate(Root<Commodity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //                //构造查询 名字模糊查询 种类精准查询
                Path<Object> name = root.get("name"); //按名字搜索
                Path<Object> category = root.get("category");//按种类搜索

                Predicate p1 = criteriaBuilder.like(name.as(String.class), "%" + conditionCom.getName() + "%");//模糊
                Predicate p2 = criteriaBuilder.equal(category, conditionCom.getCategory());//精准


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
        System.out.println("=========================");
        System.out.println(compageBean.getList());
    }

}
