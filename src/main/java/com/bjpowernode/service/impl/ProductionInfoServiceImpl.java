package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.bjpowernode.service.ProductionInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：梁栋~
 * 时间：2022/8/15 12:45
 * 描述：
 */
@Service
public class ProductionInfoServiceImpl implements ProductionInfoService {
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        ProductInfoExample productInfoExample = new ProductInfoExample();

        productInfoExample.setOrderByClause("p_id desc");

        List<ProductInfo> list = productInfoMapper.selectByExample(productInfoExample);

        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {

        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getByID(Integer id) {
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(id);
        return  productInfo;
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }


}
