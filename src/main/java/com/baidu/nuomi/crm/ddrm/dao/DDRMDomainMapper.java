package com.baidu.nuomi.crm.ddrm.dao;

import com.baidu.nuomi.crm.demo.dao.entity.DDRMDomain;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface DDRMDomainMapper {
    @Delete({
        "delete from ddrm_domain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into ddrm_domain (domain, user)",
        "values (#{domain,jdbcType=VARCHAR}, #{user,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(DDRMDomain record);

    @InsertProvider(type=DDRMDomainSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(DDRMDomain record);

    @Select({
        "select",
        "id, domain, user",
        "from ddrm_domain",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="domain", property="domain", jdbcType= JdbcType.VARCHAR),
        @Result(column="user", property="user", jdbcType= JdbcType.VARCHAR)
    })
    DDRMDomain selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DDRMDomainSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DDRMDomain record);

    @Update({
        "update ddrm_domain",
        "set domain = #{domain,jdbcType=VARCHAR},",
          "user = #{user,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DDRMDomain record);
}