package com.kong.mall.service;

import com.kong.mall.form.CartAddForm;
import com.kong.mall.form.CartUpdateForm;
import com.kong.mall.vo.CartVo;
import com.kong.mall.vo.ResponseVo;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.service
 * @author: Kong
 * @date: 2021-07-14 23:15
 **/
public interface ICartService {
    ResponseVo<CartVo> add(CartAddForm cartAddForm, Integer uid);

//    根据用户id，获取购物车信息
    ResponseVo<CartVo> list(Integer uid);

    //更新购物车
    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm cartUpdateForm);

    //删除购物车中指定商品
    ResponseVo<CartVo> delete(Integer uid, Integer productId);

    //全选
    ResponseVo<CartVo> selectAll(Integer uid);

    //全不选
    ResponseVo<CartVo> unSelectAll(Integer uid);

    //购物车中商品总和
    ResponseVo<Integer> sum(Integer uid);
}
