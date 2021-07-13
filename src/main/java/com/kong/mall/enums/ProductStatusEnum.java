package com.kong.mall.enums;

import lombok.Data;

/**
 * @program: mall
 * @description:商品状态.1-在售 2-下架 3-删除
 * @packagename: com.kong.mall.enums
 * @author: Kong
 * @date: 2021-07-13 14:38
 **/

public enum ProductStatusEnum {
    ON_SALE(1,"在售"),
    OFF_SALE(2,"下架"),
    DELETE(3,"删除");

    Integer status;
    String desc;

    ProductStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
