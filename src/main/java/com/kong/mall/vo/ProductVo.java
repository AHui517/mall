package com.kong.mall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.vo
 * @author: Kong
 * @date: 2021-07-13 09:11
 **/
@Data
public class ProductVo {

    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private Integer status;

    private BigDecimal price;

    public ProductVo() {
    }

    public ProductVo(Integer id, Integer categoryId, String name, String subtitle, String mainImage, Integer status, BigDecimal price) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.subtitle = subtitle;
        this.mainImage = mainImage;
        this.status = status;
        this.price = price;
    }
}
