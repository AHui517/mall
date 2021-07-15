package com.kong.mall.service;

import com.github.pagehelper.PageInfo;
import com.kong.mall.form.ShippingForm;
import com.kong.mall.pojo.Shipping;
import com.kong.mall.vo.ResponseVo;

import java.util.List;
import java.util.Map;

/**
 * @program: mall
 * @description: 收货地址
 * @packagename: com.kong.mall.service
 * @author: Kong
 * @date: 2021-07-15 19:41
 **/
public interface IShippingService {

    //新增地址
    ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form);

    //删除地址
    ResponseVo delete(Integer uid, Integer shippingId);

    //更新地址
    ResponseVo update(ShippingForm form, Integer shippingId, Integer uid);

    //地址列表
    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);
}
