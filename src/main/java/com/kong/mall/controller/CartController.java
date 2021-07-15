package com.kong.mall.controller;

import com.kong.mall.consts.MallConst;
import com.kong.mall.form.CartAddForm;
import com.kong.mall.form.CartUpdateForm;
import com.kong.mall.pojo.User;
import com.kong.mall.service.ICartService;
import com.kong.mall.vo.CartVo;
import com.kong.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.controller
 * @author: Kong
 * @date: 2021-07-14 21:57
 **/
//@Valid 注解，表示我们对这个对象属性需要进行验证

/**
 * 这里可以额外设置一个表单验证失败错误的拦截
 */
@RestController
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/carts")
    public ResponseVo<CartVo> list(@Valid @RequestBody CartAddForm cartAddForm,
                                  HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.list(user.getId());
    }

    @PostMapping("/carts")
    public ResponseVo<CartVo> add(@Valid @RequestBody CartAddForm cartAddForm,
                                  HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.add(cartAddForm, user.getId());
    }

    @PutMapping("/carts/{productId}")
    public ResponseVo<CartVo> update(@RequestBody CartUpdateForm form,
                                     @PathVariable Integer productId,
                                     HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.update(user.getId(), productId, form);
    }

    @DeleteMapping("/carts/{productId}")
    public ResponseVo<CartVo> delete( @PathVariable Integer productId,
                                      HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.delete(user.getId(), productId);
    }

    @PutMapping("/carts/selectAll")
    public ResponseVo<CartVo> selectAll(HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.selectAll(user.getId());
    }

    @PutMapping("/carts/unSelectAll")
    public ResponseVo<CartVo> unSelectAll(HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.unSelectAll(user.getId());
    }

    @GetMapping("/carts/products/sum")
    public ResponseVo<Integer> sum(HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.sum(user.getId());
    }
}
