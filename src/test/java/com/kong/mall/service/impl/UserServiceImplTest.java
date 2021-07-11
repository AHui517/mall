package com.kong.mall.service.impl;

import com.kong.mall.enums.ResponseEnum;
import com.kong.mall.enums.RoleEnum;
import com.kong.mall.pojo.User;
import com.kong.mall.service.IUserService;
import com.kong.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.service.impl
 * @author: Kong
 * @date: 2021-07-10 15:22
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest {

    private String username = "小赵zhao";
    private String password = "20000421";
    private String email = "keaixiaoyu@zhaoyu.com";

    @Autowired
    private IUserService userService;


    @Before
    public void register() {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(RoleEnum.ADMIN.getCode());
        userService.register(user);
    }

    @Test
    public void login() {
        ResponseVo<User> loginVo = userService.login(username, password);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), loginVo.getStatus());
    }

}