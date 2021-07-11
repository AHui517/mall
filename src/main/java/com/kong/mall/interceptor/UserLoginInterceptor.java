package com.kong.mall.interceptor;

import com.kong.mall.consts.MallConst;
import com.kong.mall.enums.ResponseEnum;
import com.kong.mall.exception.UserLoginException;
import com.kong.mall.pojo.User;
import com.kong.mall.vo.ResponseVo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: mall
 * @description: 自定义拦截器
 * @packagename: com.kong.mall
 * @author: Kong
 * @date: 2021-07-11 16:54
 * true标识继续
 * false标识中断
 **/
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User)request.getSession().getAttribute(MallConst.CURRENT_USER);
        if (user == null) {
            //这里如果返回false，那么前端将拿不到任何信息。一个好的做法是这里抛出一个异常
            throw new UserLoginException();
//            return false;
        }
        return true;
    }
}
