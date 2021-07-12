package com.kong.mall.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: mall
 * @description: 该实体类描述商品的类别
 * @packagename: com.kong.mall.pojo
 * @author: Kong
 * @date: 2021-07-08 20:10
 **/
@Data
public class Category {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sortOrder=" + sortOrder +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
