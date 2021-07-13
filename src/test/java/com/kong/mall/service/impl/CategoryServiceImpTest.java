package com.kong.mall.service.impl;

import com.kong.mall.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
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
 * @date: 2021-07-12 16:48
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class CategoryServiceImpTest {

    @Autowired
    private CategoryServiceImp iCategoryService;


    @Test
    public void selectAll() {
        iCategoryService.selectAll();
    }

    @Test
    public void findSubcategoryId() {
        List<Integer> subcategoryId = iCategoryService.findSubcategoryId(null, new ArrayList<>());
        log.info("subcategoryId={}", subcategoryId);
    }


}