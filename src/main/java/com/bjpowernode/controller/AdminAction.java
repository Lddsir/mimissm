package com.bjpowernode.controller;

import com.bjpowernode.pojo.Admin;
import com.bjpowernode.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者：梁栋~
 * 时间：2022/8/15 12:20
 * 描述：
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {
    //切记,在所有的界面层一定会有业务逻辑层的对象
    @Autowired
    AdminService adminService;
    @RequestMapping("/login")
    public String login(String name , String pwd, HttpServletRequest request){
        Admin admin = adminService.login(name,pwd);
        if (admin!=null) {
            //登录成功
            //携带数据传递到main.jsp
            request.setAttribute("admin",admin);
            return "main";
        }else{
            //登录失败
            request.setAttribute("errmsg","用户名或者密码不正确");
            return "login" ;
        }
    }
}
