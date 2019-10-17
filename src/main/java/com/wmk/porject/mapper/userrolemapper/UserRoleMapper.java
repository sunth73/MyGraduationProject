package com.wmk.porject.mapper.userrolemapper;

import com.wmk.porject.bean.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(String rec);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String rec);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole>listByUserId(Integer userId);

    int deleteByUserId(Integer userId);
}