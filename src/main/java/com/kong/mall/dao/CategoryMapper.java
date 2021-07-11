package com.kong.mall.dao;

import com.kong.mall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: mall
 * @description: category接口
 * @packagename: com.kong.mall.dao
 * @author: Kong
 * @date: 2021-07-08 20:18
 **/
@Mapper
public interface CategoryMapper {
//    注解方式
    @Select("select * from mall_category where id = #{id}")
    Category findById(@Param("id") Integer id);

//    xml配置方式
    Category queryById(Integer id);
}
