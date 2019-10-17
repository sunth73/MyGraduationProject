package com.wmk.porject.mapper.rolemapper;

import com.wmk.porject.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    String selsctAuthIds(@Param("roleid") Integer roleid);

    /**
     * 查询角色列表
     * @return
     */
    List<Role>roleList(Role role);
}