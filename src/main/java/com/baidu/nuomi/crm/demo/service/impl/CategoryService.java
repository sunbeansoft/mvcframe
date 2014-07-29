package com.baidu.nuomi.crm.demo.service.impl;

import com.baidu.nuomi.crm.demo.dao.CategoryDao;
import com.baidu.nuomi.crm.demo.service.ICategoryService;
import com.baidu.nuomi.crm.demo.service.model.CategoryRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created with IntelliJ IDEA.
 * User: mazhen01
 * Date: 2014/7/25
 * Time: 16:35
 */
@Service("categoryService")
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    @Transactional("crmTXManager")
    public CategoryRelation packageCategoryRelation(Integer id) {
        return new CategoryRelation(categoryDao.queryCategoryById(id), categoryDao.countCategory());
    }

}
