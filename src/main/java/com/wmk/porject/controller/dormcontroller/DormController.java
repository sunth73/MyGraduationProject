package com.wmk.porject.controller.dormcontroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.*;
import com.wmk.porject.request.DormScoreRequest;
import com.wmk.porject.service.dormservice.DormService;
import com.wmk.porject.service.studentservice.StudentService;
import com.wmk.porject.service.teacherservice.TeacherService;
import com.wmk.porject.util.IDUtils;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-17 16:15
 * 描述
 */
@RestController
@RequestMapping("/dorm")
public class DormController {
    @Autowired
    private DormService dormService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    /**
     * 宿舍管理员查询
     * @param JSONobj
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectDormByAdmin")
    public JSONObject selectDormByAdmin(@RequestBody JSONObject JSONobj, HttpServletRequest httpServletRequest)throws Exception{
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");
        JSONObject jsonObject=null;
        String dormClassNum=JSONobj.getString("dormClassNum");
        String dormNum=JSONobj.getString("dormNum");
        String maxDate=JSONobj.getString("maxDate");
        String minDate=JSONobj.getString("minDate");
        DormScoreRequest dormScoreRequest=new DormScoreRequest();
        if(dormClassNum!=null && !"".equals(dormClassNum)){
            dormScoreRequest.setDormClassNum(dormClassNum);
        }
        if(dormNum!=null && !"".equals(dormNum)){
            dormScoreRequest.setDormNum(dormNum);
        }
        if(maxDate!=null && !"".equals(maxDate)){
            dormScoreRequest.setMaxDate(maxDate);
        }
        if(minDate!=null && !"".equals(minDate)){
            dormScoreRequest.setMinDate(minDate);
        }

        dormScoreRequest.setCurrentUser(user.getUserid().toString());
        try {
            List<DormScoreRequest>list=dormService.selectDormByAdmin(dormScoreRequest);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }

        return jsonObject;
    }
    /**
     * 班主任查询
     * @param JSONobj
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectDormByTeacher")
    public JSONObject selectDormByTeacher(@RequestBody JSONObject JSONobj, HttpServletRequest httpServletRequest)throws Exception{
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");
        JSONObject jsonObject=null;
        String dormNum=JSONobj.getString("dormNum");
        String maxDate=JSONobj.getString("maxDate");
        String minDate=JSONobj.getString("minDate");
        DormScoreRequest dormScoreRequest=new DormScoreRequest();
        if(dormNum!=null && !"".equals(dormNum)){
            dormScoreRequest.setDormNum(dormNum);
        }
        if(maxDate!=null && !"".equals(maxDate)){
            dormScoreRequest.setMaxDate(maxDate);
        }
        if(minDate!=null && !"".equals(minDate)){
            dormScoreRequest.setMinDate(minDate);
        }
        List<DormScoreRequest>list=null;
        Teacher teacher=teacherService.selectClassNumByLoginName(user.getUsername());
        if(teacher.getTeaClassNum()==null||"".equals(teacher.getTeaClassNum())){
            return ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        }
        dormScoreRequest.setDormClassNum(teacher.getTeaClassNum());
        try {
            list=dormService.selectDormByTeacher(dormScoreRequest);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }

        return jsonObject;
    }
    /**
     * 学生查询
     * @param JSONobj
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectDormByStudent")
    public JSONObject selectDormByStudent(@RequestBody JSONObject JSONobj, HttpServletRequest httpServletRequest)throws Exception{
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");
        JSONObject jsonObject=null;
        String maxDate=JSONobj.getString("maxDate");
        String minDate=JSONobj.getString("minDate");
        DormScoreRequest dormScoreRequest=new DormScoreRequest();
        if(maxDate!=null && !"".equals(maxDate)){
            dormScoreRequest.setMaxDate(maxDate);
        }
        if(minDate!=null && !"".equals(minDate)){
            dormScoreRequest.setMinDate(minDate);
        }
        Student student=studentService.selectByStuUname(user.getUsername());
        dormScoreRequest.setDormNum(student.getStuDorm());
        try {
            List<DormScoreRequest>list=dormService.selectDormByStudent(dormScoreRequest);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }

        return jsonObject;
    }
    @RequestMapping("/selectdormScore")
    public JSONObject selectdormScore(@RequestBody JSONObject JSONobj)throws Exception{
        JSONObject jsonObject=null;
        String tdsId=JSONobj.getString("tdsId");
        try {
           DormScoreRequest dormScoreRequest=dormService.selectScore(tdsId);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,dormScoreRequest);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }

        return jsonObject;
    }
    @RequestMapping("/savepingjia")
    public JSONObject savepingjia(@RequestBody JSONObject JSONobj)throws Exception{
        JSONObject jsonObject=null;
        String tdsGrade=JSONobj.getString("tdsGrade");
        String tdsEvaluate=JSONobj.getString("tdsEvaluate");
        String tdsId=JSONobj.getString("tdsId");
        DormScore dormScore=new DormScore();
        if(tdsGrade!=null && !"".equals(tdsGrade)){
            dormScore.setTdsGrade(tdsGrade);
        }
        if(tdsEvaluate!=null && !"".equals(tdsEvaluate)){
            dormScore.setTdsEvaluate(tdsEvaluate);
        }
        dormScore.setTdsId(tdsId);
        try {
            int num=dormService.savePingJia(dormScore);
            if(num>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,num);
            }else{
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"保存失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"保存异常");
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * 宿舍列表
     * @param JSONobj
     * @return
     * @throws Exception
     */
    @RequestMapping("/dormList")
    public JSONObject dormList(@RequestBody JSONObject JSONobj)throws Exception{
        JSONObject jsonObject=null;
        try {
            List<Dorm> dormList=dormService.dromList();
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,dormList);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 添加宿舍
     * @param dorm
     * @return
     * @throws Exception
     */
    @RequestMapping("/adddorm")
    public JSONObject adddorm(@RequestBody Dorm dorm)throws Exception{
        JSONObject jsonObject=null;
        try {
            dorm.setDormId(IDUtils.createID());
            int num=dormService.addDorm(dorm);
            if(num>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,num);
            }else{
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"");
            }

        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 修改宿舍
     * @param dorm
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatedorm")
    public JSONObject updatedorm(@RequestBody Dorm dorm)throws Exception{
        JSONObject jsonObject=null;
        try {
            int num=dormService.updateDorm(dorm);
            if(num>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,num);
            }else{
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"");
            }

        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 删除宿舍
     * @param JSONArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/deletedorm")
    public JSONObject deleteclass(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<Dorm> dormList=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            System.out.println("=========="+JSONobj.toString());
            Dorm dorm=new Dorm();
            dorm.setDormId(JSONobj.getString("dormId"));
            dormList.add(dorm);
        }
        try {
            int resultNum=dormService.deleteclass(dormList);
            if(resultNum>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"删除成功");
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"删除失败");
            }
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"删除异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
}
