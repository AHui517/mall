package com.kong.mall.service.impl;

import com.google.gson.Gson;
import com.kong.mall.form.CartAddForm;
import com.kong.mall.form.CartUpdateForm;
import com.kong.mall.service.ICartService;
import com.kong.mall.vo.CartVo;
import com.kong.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
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
 * @date: 2021-07-15 10:00
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class CartServiceImplTest {

    @Autowired
    private ICartService cartService;

    @Test
    public void add() {
        CartAddForm caf = new CartAddForm();
        caf.setProductId(28);
        caf.setSelected(true);
        cartService.add(caf, 1);
    }

    @Test
    public void update() {
        ResponseVo<CartVo> update = cartService.update(1, 26, new CartUpdateForm(3, false));
        log.info("update={}", new Gson().toJson(update));
    }

    @Test
    public void delete() {
        cartService.delete(1,26);

    }

    @Test
    public void sum() {
        cartService.sum(1);
    }
}