package com.kong.mall.exception;

import com.kong.mall.enums.ResponseEnum;
import com.kong.mall.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: mall
 * @description: 捕获全局异常
 * @packagename: com.kong.mall.exception
 * @author: Kong
 * @date: 2021-07-10 23:32
 **/
@ControllerAdvice
public class RuntimeExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ResponseVo handle(RuntimeException e) {
//        return ResponseVo.error(ResponseEnum.ERROR, "意外错误");
//    }
}
