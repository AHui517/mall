package com.kong.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: mall
 * @description: 购物车
 * @packagename: com.kong.mall.vo
 * @author: Kong
 * @date: 2021-07-14 21:39
 **/
@Data
public class CartVo {
    private List<CartProductVo> cartProductVoList;

    private boolean selectAll;

    private BigDecimal cartTotalPrice;

    private Integer cartTotalQuantity;
}
