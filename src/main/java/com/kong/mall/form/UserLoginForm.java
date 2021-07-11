package com.kong.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: mall
 * @description: 接收前端的User对象的一个form
 * @packagename: com.kong.mall.form
 * @author: Kong
 * @date: 2021-07-10 21:30
 **/
@Data
public class UserLoginForm {
    //用户集合
//    @NotNull
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank
    private String password;

}
