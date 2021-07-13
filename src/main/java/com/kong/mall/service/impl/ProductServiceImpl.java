package com.kong.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kong.mall.dao.ProductMapper;
import com.kong.mall.enums.ProductStatusEnum;
import com.kong.mall.enums.ResponseEnum;
import com.kong.mall.pojo.Product;
import com.kong.mall.service.ICategoryService;
import com.kong.mall.service.IProductService;
import com.kong.mall.vo.ProductDetailVo;
import com.kong.mall.vo.ProductVo;
import com.kong.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.service.impl
 * @author: Kong
 * @date: 2021-07-13 09:19
 **/
@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        List<Integer> categoryIdList = new ArrayList<>();
        //如果传的id为null，则不需要加入到list中。
        if (categoryId != null) {
            categoryService.findSubcategoryId(categoryId, categoryIdList);
            categoryIdList.add(categoryId);
        }
        //经行分页操作
        PageHelper.startPage(pageNum, pageSize);

        List<Product> products = productMapper.selectByCategoryIdList(categoryIdList);
        log.info("products={}", products);
        List<ProductVo> productVoList = new ArrayList<>();
        for (Product product : products) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(product, productVo);
            productVoList.add(productVo);
        }

        PageInfo pageInfo = new PageInfo(products);
        pageInfo.setList(productVoList);

        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ProductDetailVo> detail(Integer productId) {
        Product product = productMapper.detail(productId);
        if (!product.getStatus().equals(ProductStatusEnum.ON_SALE.getStatus())) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);
        return ResponseVo.success(productDetailVo);
    }
}
