package com.wmk.porject.mapper.exammapper;

import com.wmk.porject.bean.Exam;

import java.util.List;

public interface ExamMapper {
    int deleteByPrimaryKey(String eId);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(String eId);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    List<Exam>examList(Exam exam);
}