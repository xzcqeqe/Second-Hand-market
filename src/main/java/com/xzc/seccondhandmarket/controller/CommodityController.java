package com.xzc.seccondhandmarket.controller;
import com.xzc.seccondhandmarket.domain.*;
import com.xzc.seccondhandmarket.service.CommodityService;
import com.xzc.seccondhandmarket.service.OrderService;
import com.xzc.seccondhandmarket.service.WaitCommodityService;
import com.xzc.seccondhandmarket.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*商品控制层
* */
@Controller
@RequestMapping("/commodity")
@SessionAttributes(value = {"login_user"})  //把登录人信息存放到session域中
public class CommodityController {
    @Autowired
    private CommodityService comService;  //已通过审核展示的商品层
    @Autowired
    private userService userService;    //用户服务层
    @Autowired
    private OrderService orderService;    //已购买的订单服务层
    @Autowired
    private WaitCommodityService waitComService;//等待处理商品的服务层
    @Autowired
    private pageBean<Commodity> pageBean;    //分页查询
    @Autowired
    private Commodity conditionCom;    //查询商品条件  防止空指针 默认没有任何值
    private Order order=new Order();
    private DateFormat df= new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    @RequestMapping("/login")//用户登录查询所有商品
    public String login(User user, Model model, HttpSession session){
        User login_reslut = userService.login(user);
        if(login_reslut!=null){//登录成功
            pageBean.setCurrentPage(1);      //登陆成功默认展示第一页
            pageBean = comService.findByPage(pageBean.getCurrentPage(),conditionCom);
            model.addAttribute("pageCom",pageBean);
            model.addAttribute("login_user",login_reslut);
            session.setAttribute("loginUser",login_reslut.getUsername());//将登录的用户存入到session中，以判别是否登录
            model.addAttribute("result","成功登录");
            model.addAttribute("condition",conditionCom);
            return "index";
            }
        else {//登录失败
            model.addAttribute("login_msg","用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/index")//返回首页  默认所有的返回 都返回第一页
    public String returnIndex(Model model, @RequestParam("currentPage")Integer integer){
        pageBean = comService.findByPage(integer,conditionCom);
        model.addAttribute("pageCom",pageBean);
        model.addAttribute("condition",conditionCom);
        return "index";
    }

    @RequestMapping("/desc")//处理每一行详细信息
    public String findDesc(@RequestParam("thisId") Integer id, Model model,@RequestParam("currentPage")Integer currentPage){

        Commodity commodityById = comService.findById(id);
        model.addAttribute("comDesc",commodityById);   //把该商品查出来返回
        model.addAttribute("currentPage",currentPage);  //把当前页查出来返回
        return "desc";
    }

    @RequestMapping("/buy")//处理购买请求
    public String buy( ModelMap modelMap, @RequestParam("thisId") Integer id){//使用modelMap从session域中获取登录人信息
        User login_user =(User) modelMap.getAttribute("login_user");//找出谁要购买（当前登录对象）
        modelMap.addAttribute("login_user",login_user);
        modelMap.addAttribute("thisId",id);  //把该商品的ID传过去
        return "Buy";
    }

    @RequestMapping("/confirm")//确认购买
    public  String confirm(ModelMap modelMap,@RequestParam("thisComId")Integer comId,
                           @RequestParam("message")String message,
                           @RequestParam("o_buyer")String o_buyer){
        User login_user =(User) modelMap.getAttribute("login_user");
        Commodity thisCom = comService.findById(comId);//取得购买商品
        order.setO_name(thisCom.getName());     //设置订单
        order.setO_money(thisCom.getMoney());
        order.setMessage(message);
        order.setO_buyer(o_buyer);
        order.setO_seller(thisCom.getUser().getUsername());
        order.setDate(df.format(new Date()));
        //调用order服务层 确认保存订单
        orderService.ConfirmList(order,login_user);//调用service方法实现双向保存
        modelMap.addAttribute("result","购买成功！即将跳转至主页。");
        return "tiaozhuan";
    }

    @RequestMapping("/add")//接受添加商品请求。跳转添加页面
    public String addComodity(ModelMap modelMap){
        User login_user = (User)modelMap.getAttribute("login_user");
        modelMap.addAttribute("login_user",login_user);
        return "addCommodity";
    }

    @RequestMapping("/wait")//接受用户发来的发布商品请求
    public String confirm(WaitCommodity waitcom, ModelMap modelMap){
        User login_user = (User)modelMap.getAttribute("login_user");
        waitComService.save(waitcom,login_user);//保存待处理商品
        modelMap.addAttribute("result","提交成功，等后台人员审核。");
        return "tiaozhuan";
    }

        //该方法中 按搜索条件查找还未实现
        @RequestMapping("/findByPage")//接受用户分页查询请求(默认每页展示8条记录)  cmmodity为搜索的条件
    public String  findByPage(Commodity commodity,@RequestParam("currentPage")Integer currentPage
            ,ModelMap modelMap){

        if(currentPage==null||"".equals(currentPage)){currentPage=1;}//防止获取不到
        conditionCom.setName(commodity.getName());
        conditionCom.setCategory(commodity.getCategory());
       pageBean = comService.findByPage(currentPage,conditionCom); //按搜索商品的属性 和分页查找
        modelMap.addAttribute("pageCom",pageBean);
        modelMap.addAttribute("condition",conditionCom);//保存查询条件
        return "index";
    }

}
