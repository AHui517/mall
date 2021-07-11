package com.kong.mall.controller;

import com.kong.mall.consts.MallConst;
import com.kong.mall.enums.ResponseEnum;
import com.kong.mall.form.UserLoginForm;
import com.kong.mall.form.UserRegisterForm;
import com.kong.mall.pojo.User;
import com.kong.mall.service.IUserService;
import com.kong.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @program: mall
 * @description: 用户注册登入的接口
 * @packagename: com.kong.mall.controller
 * @author: Kong
 * @date: 2021-07-10 17:01
 **/
@RestController
@Slf4j
public class UserController {
    /**
     * 注册模块
     * 需要判断前端传递的参数是否合法
     * @Valid  为表单校验的注解
     * @param user
     */
    @Autowired
    private IUserService iUserService;


    @PostMapping("/user/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交参数有无, {}", bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(ResponseEnum.PARAM_ERROR,"不能为空");
        }
        log.info("username={}", userForm);
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        return iUserService.register(user);
    }

    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpSession session) {
        if (bindingResult.hasErrors()) {
            return ResponseVo.error(ResponseEnum.PARAM_ERROR,"账号或密码不能为空");
        }
        ResponseVo<User> login = iUserService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        session.setAttribute(MallConst.CURRENT_USER, login.getData());
        return login;
    }

    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return  ResponseVo.success(user);
    }
    //TODO
    @PostMapping("user/logout")
    public ResponseVo logout(HttpSession session) {
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success("退出成功");
    }
}
