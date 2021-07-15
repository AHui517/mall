package com.kong.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kong.mall.dao.ShippingMapper;
import com.kong.mall.enums.ResponseEnum;
import com.kong.mall.form.ShippingForm;
import com.kong.mall.pojo.Shipping;
import com.kong.mall.service.IShippingService;
import com.kong.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mall
 * @description:
 * @packagename: com.kong.mall.service.impl
 * @author: Kong
 * @date: 2021-07-15 20:25
 **/
@Service
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form) {
        Shipping sp = new Shipping();
        BeanUtils.copyProperties(form, sp);
        sp.setUserId(uid);
        //插入语句没有生效
        int row = shippingMapper.insertSelective(sp);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        Integer id = sp.getId();
        Map<String, Integer> map = new HashMap<>();
        /**
         * 在xml配置文件中写这两个字段
         * useGeneratedKeys="true" keyProperty="id"
         * 就可以得到新增的主键
         */
        map.put("shippingId", id);
        return ResponseVo.success(map);
    }

    @Override
    public ResponseVo delete(Integer uid, Integer shippingId) {
        int row = shippingMapper.deleteByPrimaryKeyAndShippingId(uid, shippingId);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.DELETE_ADDRESS_ERROR);
        }
        return ResponseVo.successByMsg("删除地址成功");
    }

    @Override
    public ResponseVo update(ShippingForm form, Integer shippingId, Integer uid) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        shipping.setId(shippingId);
        int row = shippingMapper.updateByPrimaryKey(shipping);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.successByMsg("更新地址成功");
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(uid);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ResponseVo.success(pageInfo);
    }
}
