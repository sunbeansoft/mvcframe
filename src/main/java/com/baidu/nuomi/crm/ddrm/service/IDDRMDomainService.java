package com.baidu.nuomi.crm.ddrm.service;


import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMDomain;

import java.util.List;

/**
 * Created by sunbeansoft on 15-9-24.
 */
public interface IDDRMDomainService {

    public DDRMDomain getDDRMDomainInfo(String domain);

    public List<DDRMDomain> getAllDomain();
}
