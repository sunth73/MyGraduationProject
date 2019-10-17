package com.wmk.porject.service.attendanceservice;

import com.wmk.porject.bean.Attendance;
import com.wmk.porject.mapper.attendancemapper.AttendanceMapper;
import com.wmk.porject.mapper.studentmapper.StudentMapper;
import com.wmk.porject.request.AttResp;
import com.wmk.porject.request.AttRespnse;
import com.wmk.porject.request.AttendanceResponse;
import com.wmk.porject.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-23 12:30
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private StudentMapper studentMapper;

    public List<Attendance>attlist(Attendance attendance)throws Exception{
        return attendanceMapper.attlist(attendance);
    }
    public List<AttendanceResponse>attlistforStu(Attendance attendance)throws Exception{
        return attendanceMapper.attlistforStu(attendance);
    }

    /**
     * 回显科目
     * @param attendance
     * @return
     * @throws Exception
     */
    public List<AttendanceResponse> selectSub(Attendance attendance)throws Exception{
        return attendanceMapper.valiadlist(attendance);
    }
    /**
     * 考勤统计
     * @param attendance
     * @return
     * @throws Exception
     */
    public List<AttResp> attContentForClass(Attendance attendance)throws Exception{
        return attendanceMapper.attContentForClass(attendance);
    }

    /**
     * 添加考勤
     * @param attendance
     * @throws Exception
     */
    public void addAttendance(Attendance attendance)throws Exception{
        List<String>list= studentMapper.selectByStuClass(attendance.gettClass());
        for (String stuId:list){
            attendance.settStuId(stuId);
            attendance.settId(IDUtils.createID());
            attendance.settCause("");
            attendance.settLeave("");
            attendance.settState("");
            attendance.settWorkState("");
            attendance.settWeek("");
            attendanceMapper.insertSelective(attendance);
        }
    }

    /**
     * 修改考勤科目
     * @param attendance
     * @throws Exception
     */
    public void update(Attendance attendance)throws Exception{
        attendanceMapper.update(attendance);
    }

    /**
     * 录入考勤
     * @param list
     * @throws Exception
     */
    public void updateAtt(List<Attendance>list)throws Exception{
        for(Attendance attendance:list){
            attendanceMapper.updateByPrimaryKeySelective(attendance);
        }
    }
    public AttRespnse countNum(Attendance attendance)throws Exception{
        return attendanceMapper.selectForCountNum(attendance);
    }
}
