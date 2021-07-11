package com.kong.mall.service;

import com.kong.mall.pojo.User;
import com.kong.mall.vo.ResponseVo;

/**
 * @program: mall
 * @description: null
 * @packagename: com.kong.mall.service
 * @author: Kong
 * @date: 2021-07-10 14:11
 **/
public interface IUserService {
    /**
     * 注册
     */

    ResponseVo<User> register(User user);
    /**
     * 登入
     */
    ResponseVo<User> login(String username, String password);
}
