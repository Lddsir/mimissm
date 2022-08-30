package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 作者：梁栋~
 * 时间：2022/8/15 12:43
 * 描述：
 */
public interface ProductionInfoService {
    public List<ProductInfo> getAll();

    //分页功能实现
    PageInfo splitPage(int pageNum,int pageSize);

    int save(ProductInfo info);

    ProductInfo getByID(Integer id);

    int update(ProductInfo info);

    int delete(int pid);
}
