package com.kong.mall.service.impl;

import com.kong.mall.enums.RoleEnum;
import com.kong.mall.pojo.User;
import com.kong.mall.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Autowired
    private IUserService userService;

    @Test
    public void register() {
        User user = new User();
        user.setPassword("kong0527");
        user.setUsername("kkong");
        user.setEmail("kong@qq.com");
        user.setRole(RoleEnum.ADMIN.getCode());
        userService.register(user);
    }
}