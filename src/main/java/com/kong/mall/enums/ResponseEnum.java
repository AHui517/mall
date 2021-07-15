package com.kong.mall.enums;

import lombok.Getter;

/**
 * @program: mall
 * @description: 状态码
 * @packagename: com.kong.mall.enums
 * @author: Kong
 * @date: 2021-07-10 21:08
 **/
@Getter
public enum ResponseEnum {
    SUCCESS(0, "成功"),
    PASSWORD_ERROR(1, "密码错误"),
    USERNAME_EXIST(2, "用户已存在"),
    PARAM_ERROR(3, "参数错误"),
    EMAIL_EXIST(4, "邮箱已存在"),
    NEED_LOGIN(10, "用户未登入,请先登入"),
    ERROR(-1, "服务端错误"),
    USERNAME_OR_PASSWORD_ERROR(11, "用户名或密码错误"),
    PRODUCT_OFF_SALE_OR_DELETE(12, "商品已下架或删除"),
    PRODUCT_NOT_EXIST(13, "商品不存在"),
    PRODUCT_STOCK_ERROR(14, "库存错误"),
    CART_PRODUCT_NOT_EXIST(15, "购物车里商品不存在"),
    DELETE_ADDRESS_ERROR(16, "删除地址失败");

    Integer code;

    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
