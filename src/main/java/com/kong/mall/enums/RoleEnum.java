package com.kong.mall.enums;

import lombok.Getter;

/**
 * @program: mall
 * @description: role 的枚举类型
 * @packagename: com.kong.mall.enums
 * @author: Kong
 * @date: 2021-07-10 16:38
 * 0是管理员，1是普通用户
 **/
@Getter
public enum RoleEnum {
    ADMIN(0),
    CUSTOMER(1);

    Integer code;

    RoleEnum(Integer code) {
        this.code = code;
    }
}
