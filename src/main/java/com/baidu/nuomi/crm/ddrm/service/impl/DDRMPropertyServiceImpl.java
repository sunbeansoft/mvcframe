package com.baidu.nuomi.crm.ddrm.service.impl;

import com.baidu.nuomi.crm.ddrm.dao.PropertyDao;
import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMProperty;
import com.baidu.nuomi.crm.ddrm.service.IDDRMPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunbeansoft on 15-9-24.
 */
@Service
public class DDRMPropertyServiceImpl implements IDDRMPropertyService {

    @Autowired
    private PropertyDao propertyDao;

    @Override
    public List<DDRMProperty> getAllPropertiesByDomain(String domain) {
        return propertyDao.queryAllProperiesByDomain(domain);
    }

    @Override
    public int changePropertyValue(String domain, String propertyKey, String propertyValue) {
        return propertyDao.updatePropertyValueByDomainAndKey(domain, propertyKey, propertyValue);
    }
}
