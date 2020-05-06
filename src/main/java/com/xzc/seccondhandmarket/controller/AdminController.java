package com.xzc.seccondhandmarket.controller;


import com.xzc.seccondhandmarket.domain.Admin;
import com.xzc.seccondhandmarket.domain.User;
import com.xzc.seccondhandmarket.domain.WaitCommodity;
import com.xzc.seccondhandmarket.service.AdminService;
import com.xzc.seccondhandmarket.service.WaitCommodityService;
import com.xzc.seccondhandmarket.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import javax.servlet.http.HttpSession;
import java.util.List;
/*管理员控制层
* */
@Controller
@RequestMapping("/admin")
@SessionAttributes(value = "login_admin")//将登陆的管理员存入session域中
public class AdminController {
    @Autowired
    private WaitCommodityService waitComService;
    @Autowired
    private userService userService;
    @Autowired
    private AdminService adminService;

  @RequestMapping("/login")//管理员登录处理
  public String login(Admin admin, Model model, HttpSession session){
      Admin login_admin = adminService.login(admin);//验证登录
      if(login_admin!=null){
          //登陆成功
          List<WaitCommodity> waitCommodities = waitComService.findAll();//查询所有待处理商品给管理员
          model.addAttribute("login_admin",login_admin);
          model.addAttribute("waitComList",waitCommodities);
          session.setAttribute("login_admin",login_admin);//将登陆的管理员存入session域中 以判别是否登录
          return "adminIndex";
      }else {
          model.addAttribute("msg","用户名密码错误");
          return "adminLogin";
      }
  }

  @RequestMapping("/index")//返回首页，也就是待审批的商品页
  public  String returnIndex(ModelMap modelMap){
      List<WaitCommodity> waitCommodities = waitComService.findAll();
      modelMap.addAttribute("waitComList",waitCommodities);
      return "adminIndex";
  }

  @RequestMapping("/findAll")//查询所有用户给管理员
  public String findAll(ModelMap modelMap){
      List<User> userList = userService.findAll();
      modelMap.addAttribute("userList",userList);

      return "admin/list";
  }

  @RequestMapping("/desc/{thisId}")//管理员查看详情
   public String desc(@PathVariable("thisId")Integer id,ModelMap modelMap){
      WaitCommodity waitCommodity = waitComService.findById(id);
      modelMap.addAttribute("thisCom",waitCommodity);
      return  "admin/waitComDesc";
  }

  @RequestMapping("/pass/{thisId}")  //管理员通过该商品上架
  public  String pass(@PathVariable("thisId")Integer id,ModelMap modelMap){
      WaitCommodity waitCommodity = waitComService.findById(id);
      waitComService.pass(waitCommodity);//获得该商品 通过该申请
     modelMap.addAttribute("result","您已通过该商品审核,即将回到主页面");
       return "admin/tiaozhuan";
  }
    @RequestMapping("/refuse/{thisId}")  //管理员拒绝该商品上架
    public  String refuse(@PathVariable("thisId")Integer id,ModelMap modelMap){
        WaitCommodity waitCommodity = waitComService.findById(id);
        waitComService.refuse(waitCommodity);//获得该商品 通过该申请
        modelMap.addAttribute("result","您已成功拒绝,即将回到主页面");
        return "admin/tiaozhuan";
    }


}
