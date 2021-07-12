package com.kong.mall.service.impl;

import com.kong.mall.service.ICategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.service.impl
 * @author: Kong
 * @date: 2021-07-12 16:48
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryServiceImpTest {

    @Autowired
    private ICategoryService iCategoryService;


    @Test
    public void selectAll() {
        iCategoryService.selectAll();
    }
}