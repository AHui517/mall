package com.kong.mall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.vo
 * @author: Kong
 * @date: 2021-07-14 21:42
 **/
@Data
public class CartProductVo {
    private Integer productId;

    /**
     * 购买数量
     */
    private Integer quantity;

    private String productName;

    private String productSubtitle;

    private String productMainImage;

    private BigDecimal ProductPrice;

    private Integer productStatus;

    /**
     * 单价乘以总量
     */
    private BigDecimal productTotalPrice;

    private Integer productStock;

    private boolean productSelected;

    public CartProductVo(Integer productId, Integer quantity, String productName, String productSubtitle, String productMainImage, BigDecimal productPrice, Integer productStatus, BigDecimal productTotalPrice, Integer productStock, boolean productSelected) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productSubtitle = productSubtitle;
        this.productMainImage = productMainImage;
        this.ProductPrice = productPrice;
        this.productStatus = productStatus;
        this.productTotalPrice = productTotalPrice;
        this.productStock = productStock;
        this.productSelected = productSelected;
    }

    public CartProductVo() {
    }
}
