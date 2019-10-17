package com.wmk.porject.mapper.classmapper;

import com.wmk.porject.bean.TClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TClassMapper {
    int deleteByPrimaryKey(String tcId);

    int insert(TClass record);

    int insertSelective(TClass record);

    TClass selectByPrimaryKey(String tcId);

    int updateByPrimaryKeySelective(TClass record);

    int updateByPrimaryKey(TClass record);

    List<TClass>classList(@Param("tcGradeNum")String tcGradeNum);

    /**
     * 获取年级列表
     * @return
     */
    List<TClass>gradeList();

    /**
     * 根据班级查年级
     * @param tcClassNum
     * @return
     */
    List<TClass>selectGradeNum(@Param("tcClassNum")String tcClassNum);

    int updateByUserName(@Param("tcUserName")String tcUserName);
}