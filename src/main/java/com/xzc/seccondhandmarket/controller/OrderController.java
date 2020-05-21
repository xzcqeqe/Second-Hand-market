package com.xzc.seccondhandmarket.controller;

import com.xzc.seccondhandmarket.dao.OrderDao;
import com.xzc.seccondhandmarket.domain.User;
import com.xzc.seccondhandmarket.service.userService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/*订单控制层
* **/
@Controller
@RequestMapping("/order")
@SessionAttributes(value = {"login_user"})
public class OrderController {

    @Autowired
    private userService userService;
    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/history/{id}")//处理查看历史订单
    public String findOrder(@PathVariable("id")Integer id, ModelMap modelMap){
        User login_user = userService.findById(id);
        modelMap.addAttribute("login_user",login_user);
        return "historyList";
    }

    @RequestMapping("/delete")//删除选定的单个订单
    @Transactional
    public String deleteOrder(Integer id,ModelMap modelMap){
        orderDao.deleteById(id);
        modelMap.addAttribute("result","您已经删除所选订单，即将为您跳转到主页 ");
        return "tiaozhuan";
    }

}
