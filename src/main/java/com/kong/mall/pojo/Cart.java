package com.kong.mall.pojo;

import lombok.Data;

/**
 * @program: mall
 * @description: 购物车中需要存放在redis中的字段
 * @packagename: com.kong.mall.pojo
 * @author: Kong
 * @date: 2021-07-15 09:35
 **/
@Data
public class Cart {

    private Integer productId;

    private Integer quantity;

    private Boolean productSelected;

    public Cart(Integer productId, Integer quantity, Boolean productSelected) {
        this.productId = productId;
        this.quantity = quantity;
        this.productSelected = productSelected;
    }

    public Cart() {
    }
}
