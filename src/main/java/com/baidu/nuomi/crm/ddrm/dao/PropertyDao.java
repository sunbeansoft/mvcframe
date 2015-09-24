package com.baidu.nuomi.crm.ddrm.dao;

import com.baidu.nuomi.crm.datasource.annotation.Mapper;
import com.baidu.nuomi.crm.demo.dao.entity.DDRMProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface PropertyDao {

    public int countProperty(String domain);

    public List<DDRMProperty> queryAllProperiesByDomain(String domain);

    public int updatePropertyValueByDomainAndKey(@Param("domain") String domain, @Param("propertyKey") String propertyKey, @Param("propertyValue") String propertyValue);
}
