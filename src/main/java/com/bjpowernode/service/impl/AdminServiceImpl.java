package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：梁栋~
 * 时间：2022/8/15 12:10
 * 描述：
 */

@Service
public class AdminServiceImpl implements AdminService {
    //业务逻辑层必须有数据访问层的对象
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        //根据传入的用户名到数据库中查找相应的对象,如果查到进行密码的匹配
        AdminExample adminExample = new AdminExample();
        //如何添加条件
        adminExample.createCriteria().andANameEqualTo(name);
        List<Admin> list = adminMapper.selectByExample(adminExample);
        if (list.size()>0){
            Admin admin = list.get(0);
            //找到了这个用户,进行密码比对,这里的密码是密文,需要将传入的密码进行加密加密
            String miPwd = MD5Util.getMD5(pwd);
            if (miPwd.equals(admin.getaPass())) {
                return admin;
            }

        }
        return null;
    }
}
