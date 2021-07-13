package com.kong.mall.dao;

import com.kong.mall.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectByCategoryIdList(@Param("categoryIdList") List<Integer> categoryIdList);

    Product detail(Integer productId);
}