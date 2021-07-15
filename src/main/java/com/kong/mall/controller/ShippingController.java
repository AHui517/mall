package com.kong.mall.controller;

import com.kong.mall.consts.MallConst;
import com.kong.mall.form.ShippingForm;
import com.kong.mall.pojo.Shipping;
import com.kong.mall.pojo.User;
import com.kong.mall.service.IShippingService;
import com.kong.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * @program: mall
 * @description:收货地址接口层面
 * @packagename: com.kong.mall.controller
 * @author: Kong
 * @date: 2021-07-15 22:49
 **/

//todo  还没测试
@RestController
public class ShippingController {

    @Autowired
    private IShippingService shippingService;

    @PostMapping("/shippings")
    public ResponseVo<Map<String, Integer>> add(@Valid @RequestBody ShippingForm form,
                                                HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.add(user.getId(), form);
    }

    @DeleteMapping("/shippings/{shippingId}")
    public ResponseVo delete(@PathVariable Integer shippingId,
                             HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.delete(user.getId(), shippingId);
    }

    @PutMapping("/shippings/{shippingId}")
    public ResponseVo update(@PathVariable Integer shippingId,
                             @Valid @RequestBody ShippingForm form,
                             HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.update(form, shippingId,user.getId());
    }

    @GetMapping("/shippings")
    public ResponseVo list(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                           @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                            HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.list(user.getId(), pageNum, pageSize);
    }
}
