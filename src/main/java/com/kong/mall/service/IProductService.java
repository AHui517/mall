package com.kong.mall.service;

import com.github.pagehelper.PageInfo;
import com.kong.mall.vo.ProductDetailVo;
import com.kong.mall.vo.ProductVo;
import com.kong.mall.vo.ResponseVo;

import java.util.List;

/**
 * @program: mall
 * @description: product service
 * @packagename: com.kong.mall.service
 * @author: Kong
 * @date: 2021-07-13 09:16
 **/
public interface IProductService {
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

    ResponseVo<ProductDetailVo> detail(Integer productId);
}
