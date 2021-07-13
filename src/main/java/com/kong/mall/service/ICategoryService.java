package com.kong.mall.service;

import com.kong.mall.vo.CategoryVo;
import com.kong.mall.vo.ResponseVo;

import java.util.List;

/**
 * @program: mall
 * @description: catagory service
 * @packagename: com.kong.mall.service
 * @author: Kong
 * @date: 2021-07-12 16:19
 **/
public interface ICategoryService {
    ResponseVo<List<CategoryVo>> selectAll();

    List<Integer> findSubcategoryId(Integer id, List<Integer> subCategoryIdList);
}
