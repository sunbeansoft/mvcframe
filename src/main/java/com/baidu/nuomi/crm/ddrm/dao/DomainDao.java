package com.baidu.nuomi.crm.ddrm.dao;

import com.baidu.nuomi.crm.datasource.annotation.Mapper;
import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMDomain;

import java.util.List;


@Mapper
public interface DomainDao {

    public int countDomain();

    public DDRMDomain queryDDRMDomainInfoByName(String domain);

    public List<DDRMDomain> queryAllDomain();
}
