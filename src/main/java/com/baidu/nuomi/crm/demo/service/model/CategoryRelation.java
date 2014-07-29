package com.baidu.nuomi.crm.demo.service.model;

import com.baidu.nuomi.crm.demo.dao.entity.Category;

import java.util.Date;

/**
 * 封装了个属性isMiddle，判断id是否为总数的中间，演示用，没意思
 * User: mazhen01
 * Date: 2014/7/25
 * Time: 16:54
 */
public class CategoryRelation {

    private Integer id;
    private String name;
    private Boolean isMiddle;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean isMiddle() {
        return this.isMiddle;
    }

    public CategoryRelation(Category category, Integer totalCount) {
        if (category == null || totalCount == null) {
            return;
        }

        this.id = category.getId();
        this.name = category.getName();
        this.date = new Date();
        isMiddle = totalCount / 2 == id ? true : false;
    }
}
