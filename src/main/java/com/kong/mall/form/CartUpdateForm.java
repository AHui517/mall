package com.kong.mall.form;

import lombok.Data;

/**
 * @program: mall
 * @description: 更新购物车
 * @packagename: com.kong.mall.form
 * @author: Kong
 * @date: 2021-07-15 15:27
 **/
@Data
public class CartUpdateForm {

    private Integer quantify;

    private Boolean selected;

    public CartUpdateForm() {
    }

    public CartUpdateForm(Integer quantify, Boolean selected) {
        this.quantify = quantify;
        this.selected = selected;
    }
}
