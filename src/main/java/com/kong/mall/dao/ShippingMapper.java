package com.kong.mall.dao;

import com.kong.mall.pojo.Shipping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByPrimaryKeyAndShippingId(@Param("uid") Integer uid,
                                        @Param("shipping") Integer shippingId);

    List<Shipping> selectByUserId(Integer uid);
}