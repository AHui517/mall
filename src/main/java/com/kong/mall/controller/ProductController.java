package com.kong.mall.controller;

import com.github.pagehelper.PageInfo;
import com.kong.mall.service.IProductService;
import com.kong.mall.vo.ProductDetailVo;
import com.kong.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall
 * @description: null
 * @packagename: com.kong.mall.controller
 * @author: Kong
 * @date: 2021-07-13 13:24
 **/
@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ResponseVo<PageInfo> list(@RequestParam(required = false) Integer categoryId,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return productService.list(categoryId, pageNum, pageSize);
    }

    //在url里的参数要用@PathVariable注解
    @GetMapping("/products/{productId}")
    public ResponseVo<ProductDetailVo> detail(@PathVariable("productId") Integer productId) {
        return productService.detail(productId);
    }
}
