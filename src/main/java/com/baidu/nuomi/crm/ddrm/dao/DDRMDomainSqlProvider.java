package com.baidu.nuomi.crm.ddrm.dao;

import com.baidu.nuomi.crm.demo.dao.entity.DDRMDomain;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class DDRMDomainSqlProvider {

    public String insertSelective(DDRMDomain record) {
        BEGIN();
        INSERT_INTO("ddrm_domain");
        
        if (record.getDomain() != null) {
            VALUES("domain", "#{domain,jdbcType=VARCHAR}");
        }
        
        if (record.getUser() != null) {
            VALUES("user", "#{user,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(DDRMDomain record) {
        BEGIN();
        UPDATE("ddrm_domain");
        
        if (record.getDomain() != null) {
            SET("domain = #{domain,jdbcType=VARCHAR}");
        }
        
        if (record.getUser() != null) {
            SET("user = #{user,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}