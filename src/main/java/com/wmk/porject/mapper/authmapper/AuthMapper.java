package com.wmk.porject.mapper.authmapper;

import com.wmk.porject.bean.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuthMapper {
    int deleteByPrimaryKey(Integer authid);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Integer authid);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Auth>getAuthByParentId(Map<String, Object> paramMap);
    List<Auth> selectByParentId(Integer parentid);

}