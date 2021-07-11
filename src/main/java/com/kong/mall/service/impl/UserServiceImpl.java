package com.kong.mall.service.impl;

import com.kong.mall.dao.UserMapper;
import com.kong.mall.enums.ResponseEnum;
import com.kong.mall.pojo.User;
import com.kong.mall.service.IUserService;
import com.kong.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @program: mall
 * @description: null
 * @packagename: com.kong.mall.service.impl
 * @author: Kong
 * @date: 2021-07-10 14:17
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseVo<User> register(User user) {
        user.setRole(0);
        //username和email不能重复
        int usernameCount = userMapper.countByUsername(user.getUsername());
        int emailCount = userMapper.countByEmail(user.getEmail());
        if (usernameCount > 0) {
//            throw new RuntimeException("该username已注册");
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }
        if (emailCount > 0) {
//            throw new RuntimeException("该email已注册");
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }

        //MD5摘要算法
        String md5paw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(md5paw);

        //写入数据库
        int resultCount = userMapper.insertSelective(user);
        //插入失败
        if (resultCount == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        //数据库里面压根没有这个用户
        if (user == null) {
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //这个是密码错误
        String md5paw = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!md5paw.equalsIgnoreCase(user.getPassword())) {
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        user.setPassword("");
        return ResponseVo.success(user);
    }
}
