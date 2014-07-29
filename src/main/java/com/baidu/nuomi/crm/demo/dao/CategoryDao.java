package com.baidu.nuomi.crm.demo.dao;

import com.baidu.nuomi.crm.datasource.annotation.Mapper;
import com.baidu.nuomi.crm.demo.dao.entity.Category;

/**
 * Created with IntelliJ IDEA.
 * User: mazhen01
 * Date: 2014/7/25
 * Time: 15:46
 */
@Mapper
public interface CategoryDao {

    public int countCategory();

    public Category queryCategoryById(Integer id);
}
