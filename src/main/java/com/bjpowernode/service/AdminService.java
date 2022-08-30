package com.bjpowernode.service;

import com.bjpowernode.pojo.Admin;

/**
 * 作者：梁栋~
 * 时间：2022/8/15 12:09
 * 描述：
 */
public interface AdminService {
    //登录判断
     Admin login(String name, String pwd);
}
