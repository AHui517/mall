package com.kong.mall.controller;

import com.kong.mall.service.ICategoryService;
import com.kong.mall.vo.CategoryVo;
import com.kong.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.controller
 * @author: Kong
 * @date: 2021-07-12 17:59
 **/
@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseVo<List<CategoryVo>> selectAll() {
        ResponseVo<List<CategoryVo>> listResponseVo = categoryService.selectAll();
        System.out.println(listResponseVo);
        return listResponseVo;
    }

}
