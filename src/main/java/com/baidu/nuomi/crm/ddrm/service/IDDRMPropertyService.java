package com.baidu.nuomi.crm.ddrm.service;


import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMProperty;

import java.util.List;

/**
 * Created by sunbeansoft on 15-9-24.
 */
public interface IDDRMPropertyService {

    public List<DDRMProperty> getAllPropertiesByDomain(String domain);

    public int changePropertyValue(String domain, String propertyKey, String propertyValue);
}
