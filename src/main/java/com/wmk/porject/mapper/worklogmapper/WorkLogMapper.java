package com.wmk.porject.mapper.worklogmapper;

import com.wmk.porject.bean.WorkLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkLogMapper {
    int deleteByPrimaryKey(String tId);

    int insert(WorkLog record);

    int insertSelective(WorkLog record);

    WorkLog selectByPrimaryKey(String tId);

    int updateByPrimaryKeySelective(WorkLog record);

    int updateByPrimaryKey(WorkLog record);

    List<WorkLog> logList(WorkLog workLog);
}