package com.kong.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.kong.mall.dao.ProductMapper;
import com.kong.mall.enums.ResponseEnum;
import com.kong.mall.service.ICategoryService;
import com.kong.mall.service.IProductService;
import com.kong.mall.vo.ProductDetailVo;
import com.kong.mall.vo.ProductVo;
import com.kong.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.service.impl
 * @author: Kong
 * @date: 2021-07-13 11:20
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductServiceImplTest {

    @Autowired
    private IProductService productService;


    @Test
    public void list() {
        ResponseVo<PageInfo> list = productService.list(null, 1, 2);
        Assert.assertEquals(list.getStatus(), ResponseEnum.SUCCESS.getCode());
    }

    @Test
    public void TestDetail(){
        ResponseVo<ProductDetailVo> detail = productService.detail(26);
        Assert.assertEquals(detail.getStatus(), ResponseEnum.SUCCESS.getCode());
    }
}