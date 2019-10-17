package com.wmk.porject.mapper.subjectmapper;

import com.wmk.porject.bean.Subject;

import java.util.List;

public interface SubjectMapper {
    int deleteByPrimaryKey(String id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    List<Subject>sublict();
}