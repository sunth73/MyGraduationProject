package com.wmk.porject.mapper.attendancemapper;

import com.wmk.porject.bean.Attendance;
import com.wmk.porject.request.AttResp;
import com.wmk.porject.request.AttRespnse;
import com.wmk.porject.request.AttendanceResponse;

import java.util.List;

public interface AttendanceMapper {
    int deleteByPrimaryKey(String tId);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(String tId);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);

    int update(Attendance record);

    List<Attendance>attlist(Attendance attendance);
    List<AttendanceResponse>attlistforStu(Attendance attendance);
    /**
     * 验证该节课是否已经考勤
     * @param attendance
     * @return
     */
    List<AttendanceResponse>valiadlist(Attendance attendance);

    List<AttResp>attContentForClass(Attendance attendance);
    AttRespnse selectForCountNum(Attendance attendance);
}