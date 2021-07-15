package com.kong.mall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: mall
 * @description: 用户添加进购物车时需要的参数
 * @packagename: com.kong.mall.form
 * @author: Kong
 * @date: 2021-07-14 21:54
 **/
@Data
public class CartAddForm {
    //@NotBlank 用于String判断空格
    //@NotEmpty 用于判断集合是否为空
    //@NotNull
    @NotNull
    private Integer productId;

    private Boolean selected = true;
}
