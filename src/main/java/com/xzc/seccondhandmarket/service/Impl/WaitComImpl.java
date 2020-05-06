package com.xzc.seccondhandmarket.service.Impl;
import com.xzc.seccondhandmarket.dao.CommodityDao;
import com.xzc.seccondhandmarket.dao.WaitCommodityDao;
import com.xzc.seccondhandmarket.domain.Commodity;
import com.xzc.seccondhandmarket.domain.User;
import com.xzc.seccondhandmarket.domain.WaitCommodity;
import com.xzc.seccondhandmarket.service.WaitCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service//待处理订单的业务层
public class WaitComImpl implements WaitCommodityService {
    @Autowired
    WaitCommodityDao waitCommodityDao;

    @Autowired
    CommodityDao commodityDao;

    @Override
    @Transactional
    public void save(WaitCommodity waitcom, User login_user) {//将等待处理的订单和用户保存
        login_user.getWaitCommodities().add(waitcom);
        waitcom.setUser(login_user);
        waitCommodityDao.save(waitcom);
    }

    @Override
    public List<WaitCommodity> findAll() {//查询所有待处理订单
        List<WaitCommodity> waitCommodityList = waitCommodityDao.findAll();
        return  waitCommodityList;
    }

    @Override
    public WaitCommodity findById(Integer id) {//根据Id查找待处理商品
        WaitCommodity waitCommodity = waitCommodityDao.findById(id).orElse(null);
        return waitCommodity;
    }

    @Override
    @Transactional
    public void pass(WaitCommodity waitCommodity) {//处理商品通过审核
        Commodity commodity = new Commodity();//创建审核通过的商品
        //让正处理的商品添加到审核过后的商品
        commodity.setName(waitCommodity.getName());
        commodity.setCategory(waitCommodity.getCategory());
        commodity.setDes(waitCommodity.getDes());
        commodity.setMoney(waitCommodity.getMoney());
        User user = waitCommodity.getUser();
        commodity.setUser(user);
        user.getCommodityList().add(commodity);
        commodityDao.save(commodity);      //保存到已通过表中
        waitCommodityDao.deleteById(waitCommodity.getW_id());//从待处理的商品表删除
    }

    @Override
    @Transactional
    public void refuse(WaitCommodity waitCommodity) {//处理该商品不通过
        waitCommodityDao.deleteById(waitCommodity.getW_id());  //直接从待处理表删除
    }
}
