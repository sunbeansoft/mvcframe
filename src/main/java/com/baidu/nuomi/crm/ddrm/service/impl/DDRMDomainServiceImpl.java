package com.baidu.nuomi.crm.ddrm.service.impl;

import com.baidu.nuomi.crm.ddrm.dao.DomainDao;
import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMDomain;
import com.baidu.nuomi.crm.ddrm.service.IDDRMDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunbeansoft on 15-9-24.
 */
@Service
public class DDRMDomainServiceImpl implements IDDRMDomainService {
    @Autowired
    private DomainDao domainDao;

    @Override
    public DDRMDomain getDDRMDomainInfo(String domain) {
        return domainDao.queryDDRMDomainInfoByName(domain);
    }

    @Override
    public List<DDRMDomain> getAllDomain() {
        return domainDao.queryAllDomain();
    }
}
