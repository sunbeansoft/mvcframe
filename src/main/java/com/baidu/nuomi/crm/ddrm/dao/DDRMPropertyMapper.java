package com.baidu.nuomi.crm.ddrm.dao;

import com.baidu.nuomi.crm.demo.dao.entity.DDRMProperty;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface DDRMPropertyMapper {
    @Delete({
        "delete from ddrm_property",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into ddrm_property (domain, property_key, ",
        "property_value, create_time, ",
        "update_time)",
        "values (#{domain,jdbcType=VARCHAR}, #{propertyKey,jdbcType=VARCHAR}, ",
        "#{propertyValue,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(DDRMProperty record);

    @InsertProvider(type=DDRMPropertySqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(DDRMProperty record);

    @Select({
        "select",
        "id, domain, property_key, property_value, create_time, update_time",
        "from ddrm_property",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="domain", property="domain", jdbcType= JdbcType.VARCHAR),
        @Result(column="property_key", property="propertyKey", jdbcType= JdbcType.VARCHAR),
        @Result(column="property_value", property="propertyValue", jdbcType= JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP)
    })
    DDRMProperty selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DDRMPropertySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DDRMProperty record);

    @Update({
        "update ddrm_property",
        "set domain = #{domain,jdbcType=VARCHAR},",
          "property_key = #{propertyKey,jdbcType=VARCHAR},",
          "property_value = #{propertyValue,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DDRMProperty record);
}