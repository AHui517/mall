package com.kong.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.vo
 * @author: Kong
 * @date: 2021-07-13 14:27
 **/
@Data
public class ProductDetailVo {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private String subImages;

    private String detail;

    private Date createTime;

    private Date updateTime;
}
