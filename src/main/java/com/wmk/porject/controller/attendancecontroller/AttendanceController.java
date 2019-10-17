package com.wmk.porject.controller.attendancecontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.*;
import com.wmk.porject.request.AttResp;
import com.wmk.porject.request.AttRespnse;
import com.wmk.porject.request.AttendanceResponse;
import com.wmk.porject.service.attendanceservice.AttendanceService;
import com.wmk.porject.service.studentservice.StudentService;
import com.wmk.porject.util.IDUtils;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-23 12:33
 * 描述
 */
@RestController
@RequestMapping("/att")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private StudentService studentService;
    @RequestMapping("/list")
    public JSONObject list(@RequestBody Attendance attendance)throws Exception{

        List<Attendance> list= null;
        JSONObject jsonObject=null;
        try {

            list = attendanceService.attlist(attendance);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 回显科目
     * @param attendance
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectsub")
    public JSONObject selectsub(@RequestBody Attendance attendance)throws Exception{

        List<AttendanceResponse> list= null;
        JSONObject jsonObject=null;
        try {

            list = attendanceService.selectSub(attendance);
            if(list.size()>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list.get(0));
            }else {
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,null);
            }

        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 查询考勤
     * @param attendance
     * @return
     * @throws Exception
     */
    @RequestMapping("/validatelist")
    public JSONObject validatelist(@RequestBody Attendance attendance)throws Exception{

        List<Attendance> list= null;
        JSONObject jsonObject=null;
        try {
            List<AttendanceResponse> strinLlist=attendanceService.selectSub(attendance);
            if(CollectionUtils.isEmpty(strinLlist)){
                return ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"0");
            }else if(strinLlist.size()>0&&strinLlist.get(0).gettSubId().equals(attendance.gettSubId())){
                list = attendanceService.attlist(attendance);
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
            }else if(strinLlist.size()>0&&(!strinLlist.get(0).gettSubId().equals(attendance.gettSubId()))){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"1");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 添加考勤
     * @param attendance
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")
    public JSONObject addAtt(@RequestBody Attendance attendance)throws Exception{

        JSONObject jsonObject=null;
        try {
            attendanceService.addAttendance(attendance);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,null);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"操作异常");
            e.printStackTrace();
        }

        return jsonObject;
    }
    /**
     * 修改考勤科目
     * @param attendance
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public JSONObject update(@RequestBody Attendance attendance)throws Exception{

        JSONObject jsonObject=null;
        try {
            attendanceService.update(attendance);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,null);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"操作异常");
            e.printStackTrace();
        }

        return jsonObject;
    }
    /**
     * 录入考勤
     * @param JSONArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateatt")
    public JSONObject updateatt(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<Attendance> list=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            Attendance attendance=new Attendance();
            attendance.settId(JSONobj.getString("tId"));
            attendance.settCause(JSONobj.getString("tCause"));

            if("未填写".equals(JSONobj.getString("tState"))){
                attendance.settState("");
            }else if("正常".equals(JSONobj.getString("tState"))){
                attendance.settState("0");
            }else if("迟到".equals(JSONobj.getString("tState"))){
                attendance.settState("1");
            }else if("早退".equals(JSONobj.getString("tState"))){
                attendance.settState("2");
            }else if("迟到并早退".equals(JSONobj.getString("tState"))){
                attendance.settState("3");
            }
            if("未填写".equals(JSONobj.getString("tWorkState"))){
                attendance.settWorkState("");
            }else if("出勤".equals(JSONobj.getString("tWorkState"))){
                attendance.settWorkState("0");
            }else if("缺勤".equals(JSONobj.getString("tWorkState"))){
                attendance.settWorkState("1");
            }else if("请假".equals(JSONobj.getString("tWorkState"))){
                attendance.settWorkState("2");
            }
            attendance.settWeek("");
            list.add(attendance);
        }
        try {
            attendanceService.updateAtt(list);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,null);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"考勤异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 考勤统计
     * @param attendance
     * @return
     * @throws Exception
     */
    @RequestMapping("/attContentForClass")
    public JSONObject attContentForClass(@RequestBody Attendance attendance)throws Exception{

        List<AttResp> list= null;
        JSONObject jsonObject=null;
        try {

            list = attendanceService.attContentForClass(attendance);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 学生个人查询
     * @param attendance
     * @return
     * @throws Exception
     */
    @RequestMapping("/attlistforStu")
    public JSONObject attlistforStu(@RequestBody Attendance attendance)throws Exception{
            Student student=studentService.selectByUserId(Integer.parseInt(attendance.getUserid()));
        attendance.settStuId(student.getStuId());
        List<AttendanceResponse> list= null;
        JSONObject jsonObject=null;
        try {

            list = attendanceService.attlistforStu(attendance);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 分类人数统计
     * @param attendance
     * @return
     * @throws Exception
     */
    @RequestMapping("/countNum")
    public JSONObject countNum(@RequestBody Attendance attendance)throws Exception{
        Student student=null;
        if(!StringUtils.isEmpty(attendance.getUserid())){
            student=studentService.selectByUserId(Integer.parseInt(attendance.getUserid()));
        }
        if (!ObjectUtils.isEmpty(student)){
            attendance.settStuId(student.getStuId());
        }
        JSONObject jsonObject=null;
        try {

            AttRespnse attRespnse = attendanceService.countNum(attendance);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,attRespnse);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
}
