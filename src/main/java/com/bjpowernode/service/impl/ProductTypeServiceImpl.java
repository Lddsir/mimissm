package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductTypeMapper;
import com.bjpowernode.pojo.ProductType;
import com.bjpowernode.pojo.ProductTypeExample;
import com.bjpowernode.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：梁栋~
 * 时间：2022/8/15 15:56
 * 描述：
 */
@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    ProductTypeMapper mapper;

    @Override
    public List<ProductType> getAll() {
        return mapper.selectByExample(new ProductTypeExample());
    }
}
