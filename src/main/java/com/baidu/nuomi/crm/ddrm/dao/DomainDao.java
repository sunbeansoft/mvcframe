package com.baidu.nuomi.crm.ddrm.dao;

import com.baidu.nuomi.crm.datasource.annotation.Mapper;
import com.baidu.nuomi.crm.demo.dao.entity.DDRMDomain;

import java.util.List;


@Mapper
public interface DomainDao {

    public int countDomain();

    public List<DDRMDomain> queryAllDomain();
}
