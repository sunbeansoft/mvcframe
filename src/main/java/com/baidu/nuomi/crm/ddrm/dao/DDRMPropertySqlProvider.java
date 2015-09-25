package com.baidu.nuomi.crm.ddrm.dao;



import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMProperty;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class DDRMPropertySqlProvider {

    public String insertSelective(DDRMProperty record) {
        BEGIN();
        INSERT_INTO("ddrm_property");
        
        if (record.getDomain() != null) {
            VALUES("domain", "#{domain,jdbcType=VARCHAR}");
        }
        
        if (record.getPropertyKey() != null) {
            VALUES("property_key", "#{propertyKey,jdbcType=VARCHAR}");
        }
        
        if (record.getPropertyValue() != null) {
            VALUES("property_value", "#{propertyValue,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(DDRMProperty record) {
        BEGIN();
        UPDATE("ddrm_property");
        
        if (record.getDomain() != null) {
            SET("domain = #{domain,jdbcType=VARCHAR}");
        }
        
        if (record.getPropertyKey() != null) {
            SET("property_key = #{propertyKey,jdbcType=VARCHAR}");
        }
        
        if (record.getPropertyValue() != null) {
            SET("property_value = #{propertyValue,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}