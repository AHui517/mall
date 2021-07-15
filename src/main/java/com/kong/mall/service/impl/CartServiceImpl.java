package com.kong.mall.service.impl;

import com.google.gson.Gson;
import com.kong.mall.dao.ProductMapper;
import com.kong.mall.enums.ProductStatusEnum;
import com.kong.mall.enums.ResponseEnum;
import com.kong.mall.form.CartAddForm;
import com.kong.mall.form.CartUpdateForm;
import com.kong.mall.pojo.Cart;
import com.kong.mall.pojo.Product;
import com.kong.mall.service.ICartService;
import com.kong.mall.vo.CartProductVo;
import com.kong.mall.vo.CartVo;
import com.kong.mall.vo.ResponseVo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.service.impl
 * @author: Kong
 * @date: 2021-07-14 23:17
 **/
@Service
public class CartServiceImpl implements ICartService {

    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    private Gson gson = new Gson();

    //每次往购物车中添加默认数量为1
    private Integer quantify = 1;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResponseVo<CartVo> add(CartAddForm cartAddForm, Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        Product product = productMapper.detail(cartAddForm.getProductId());
        //判断商品是否存在
        if (product == null) {
            ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }

        //商品是否正常在售
        if (!product.getStatus().equals(ProductStatusEnum.ON_SALE)) {
            ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }

        //判断商品库存是否充足
        if (product.getStock() <= 0) {
            return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR);
        }

        //写入redis
        //第一个是cart_×，第二个是产品的id，第三个是对应信息
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        String productId = product.getId() + "";

        //这个用户这个购物车中的指定productId的信息
        String value = opsForHash.get(redisKey, productId);

//        这个Cart用来保存新增的商品信息或者修改数量后的商品信息
        Cart cart;
        if (StringUtil.isNullOrEmpty(value)) {
            //该用户的购物车中没有该商品，新增
            cart = new Cart(product.getId(), quantify, cartAddForm.getSelected());
        } else {
            //该产品数量加1，反序列化
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(cart.getQuantity() + quantify);
        }
        //最后将信息添加到redis中
        opsForHash.put(redisKey, productId, gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {

        CartVo cartVo = new CartVo();
        List<CartProductVo> list = new ArrayList<>();

        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        //从redis中根据用户id获取所有商品信息
        Map<String, String> entries = opsForHash.entries(redisKey);

        //购物车中所有物品的总价
        BigDecimal totalPrice = BigDecimal.ZERO;

        //购物车中商品的数量
        Integer totalQuantity = 0;

        //是否全选
        Boolean selectAll = true;

        for (Map.Entry<String, String> entry : entries.entrySet()) {

            //redis中存储的每个商品的信息。
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            Integer productId = Integer.valueOf(entry.getKey());
            //TODO 这里多次向数据库中查询，需要优化
            Product product = productMapper.detail(productId);

            if (product != null) {
                CartProductVo cpv = new CartProductVo(
                        productId,
                        cart.getQuantity(),
                        product.getName(),
                        product.getSubtitle(),
                        product.getMainImage(),
                        product.getPrice(),
                        product.getStatus(),
                        product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())),
                        product.getStock(),
                        cart.getProductSelected()
                );
                list.add(cpv);

                if (!cart.getProductSelected()) {
                    selectAll = false;
                }

                //计算总价
                totalPrice = totalPrice.add(cpv.getProductTotalPrice());

            }
            //购物车商品总数,只计算选中的
            if (cart.getProductSelected()) {
                totalQuantity += cart.getQuantity();
            }
        }
        cartVo.setCartProductVoList(list);
        cartVo.setSelectAll(selectAll);
        cartVo.setCartTotalPrice(totalPrice);
        cartVo.setCartTotalQuantity(totalQuantity);
        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm cartUpdateForm) {
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String value = opsForHash.get(redisKey, productId + "");
        if (StringUtil.isNullOrEmpty(value)) {
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }

        //反序列化出来
        Cart cart = gson.fromJson(value, Cart.class);
        Integer updateQuantify = cartUpdateForm.getQuantify();
        Boolean updateSelected = cartUpdateForm.getSelected();
        //如果这两个参数都穿了并合规，就修改
        if (updateSelected != null && updateQuantify >= 0) {
            cart.setQuantity(updateQuantify);
        }
        if (updateSelected != null) {
            cart.setProductSelected(updateSelected);
        }
        //就修改后的数据重新加入到redis中
        opsForHash.put(redisKey, String.valueOf(productId),
                gson.toJson(cart));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> delete(Integer uid, Integer productId) {
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String value = opsForHash.get(redisKey, String.valueOf(productId));
        if (StringUtil.isNullOrEmpty(value)) {
            //没有该商品
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }
        opsForHash.delete(redisKey, String.valueOf(productId));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> selectAll(Integer uid) {
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> map = opsForHash.entries(redisKey);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            cart.setProductSelected(true);
            opsForHash.put(redisKey, cart.getProductId() + "",
                    gson.toJson(cart));
        }
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> unSelectAll(Integer uid) {
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> map = opsForHash.entries(redisKey);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            cart.setProductSelected(false);
            opsForHash.put(redisKey, cart.getProductId() + "",
                    gson.toJson(cart));
        }
        return list(uid);
    }

    @Override
    public ResponseVo<Integer> sum(Integer uid) {
        Integer count = 0;
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> map = opsForHash.entries(redisKey);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Integer quantity = gson.fromJson(entry.getValue(), Cart.class).getQuantity();
            if (quantity != null && quantity >= 0) {
                count += quantity;
            }
        }
        return ResponseVo.success(count);
    }
}
