package com.xzc.seccondhandmarket.controller;
import com.xzc.seccondhandmarket.domain.ResultInfo;
import com.xzc.seccondhandmarket.domain.User;
import com.xzc.seccondhandmarket.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/*用户控制层
* */
@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService userService;
    @Autowired//info 返回响应结果信息
    private ResultInfo info;

    //处理注册请求
    @RequestMapping(value = "/registerVerify")
    @ResponseBody
    public ResultInfo register(User user){
        boolean flag = userService.register(user);
        if(flag){//注册成功
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("注册失败，用户名已经存在");
        }
        return info;
    }

}
