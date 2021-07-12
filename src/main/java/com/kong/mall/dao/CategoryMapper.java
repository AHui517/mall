package com.kong.mall.dao;

import com.kong.mall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: mall
 * @description: category接口
 * @packagename: com.kong.mall.dao
 * @author: Kong
 * @date: 2021-07-08 20:18
 **/
@Mapper
public interface CategoryMapper {

    List<Category> selectAll();
}
