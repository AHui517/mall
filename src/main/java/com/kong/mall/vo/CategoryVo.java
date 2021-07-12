package com.kong.mall.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description: catagory的视图
 * @packagename: com.kong.mall.vo
 * @author: Kong
 * @date: 2021-07-12 16:20
 **/
@Data
public class CategoryVo {

    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    List<CategoryVo> categoryVoList;
}
