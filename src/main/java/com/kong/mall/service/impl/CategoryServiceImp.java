package com.kong.mall.service.impl;

import com.kong.mall.dao.CategoryMapper;
import com.kong.mall.pojo.Category;
import com.kong.mall.service.ICategoryService;
import com.kong.mall.vo.CategoryVo;
import com.kong.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: mall
 * @description: null
 * @packagename: com.kong.mall.service.impl
 * @author: Kong
 * @date: 2021-07-12 16:32
 **/
@Slf4j
@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        //查询数据库中所有的category(类目)
        List<Category> categoryList = categoryMapper.selectAll();

        //这一步是将类目中parent_id这个字段为0的类目查询出来
        //并且将他转变为一个categoryVo对象 并加入到列表中。
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getParentId() == 0) {
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                categoryVoList.add(categoryVo);
            }
        }

        /**
         * lambda + stream
         * List<CategoryVo> categoryVos = categoryList.stream()
         *                 .filter(e -> e.getParentId() == 0)
         *                 .map(e -> category2CategoryVo(e))
         *                 .collect(Collectors.toList());
         */


        //将父类目按sort_order排序
        Collections.sort(categoryVoList, new Comparator<CategoryVo>() {
            @Override
            public int compare(CategoryVo o1, CategoryVo o2) {
                return o2.getSortOrder() - o1.getSortOrder();
            }
        });


        //递归查找子类目
        findSubCategory(categoryList, categoryVoList);

        return ResponseVo.success(categoryVoList);
    }

    /**
     * 将category映射为categoryVo
     * @param category
     * @return
     */
    private CategoryVo category2CategoryVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }

    /**
     * 这里是递归寻找子目录
     * categoryList是数据库中的所有类目，categoryVoList是顶级父目录
     * 两个类目的parent_id和id相等时，说明这两个类目存在父子关系
     */
    private void findSubCategory(List<Category> categoryList, List<CategoryVo> categoryVoList) {
        for (CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoryList = new ArrayList<>();
            for (Category category : categoryList) {
                if (category.getParentId().equals(categoryVo.getId())) {
                    subCategoryList.add(category2CategoryVo(category));
                }
            }
            //对子标签进行排序
            Collections.sort(subCategoryList, new Comparator<CategoryVo>() {
                @Override
                public int compare(CategoryVo o1, CategoryVo o2) {
                    return o2.getSortOrder() - o1.getSortOrder();
                }
            });
            //已找到该类目的所有子类目。
            categoryVo.setCategoryVoList(subCategoryList);
            //递归查找子类目的子类目。
            findSubCategory(categoryList, subCategoryList);
        }
    }


    /**
     * 该方法用于查询指定类目下的所有子类目(不包含本身)
     * @param id
     * @param subCategoryIdList
     * @return
     */
    @Override
    public List<Integer> findSubcategoryId(Integer id, List<Integer> subCategoryIdList) {
        List<Category> categoryList = categoryMapper.selectAll();

        return findSubcategoryId(id, subCategoryIdList, categoryList);
    }

    //递归寻找子目录的id,避免多次向数据库查找
    private List<Integer> findSubcategoryId(Integer id, List<Integer> subCategoryIdList, List<Category> categoryList) {
        for (Category category : categoryList) {
            if (category.getParentId().equals(id)) {
                //存在父子类目关系，继续往下查询。
                Integer subCategoryId = category.getId();
                subCategoryIdList.add(subCategoryId);
                findSubcategoryId(subCategoryId, subCategoryIdList);
            }
        }
        return subCategoryIdList;
    }

}
