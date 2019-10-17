package com.wmk.porject.controller.subsidycontroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.*;
import com.wmk.porject.request.ScoreRequest;
import com.wmk.porject.service.studentservice.StudentService;
import com.wmk.porject.service.subsidyservice.SubsidyService;
import com.wmk.porject.service.teacherservice.TeacherService;
import com.wmk.porject.util.IDUtils;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-20 16:34
 * 描述
 */
@RestController
@RequestMapping("/subsidy")
public class SubsidyController {
    @Autowired
    private SubsidyService subsidyService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    /**
     * 助学金列表
     * @return
     * @throws Exception
     */
    @RequestMapping("/subsidylist")
    public JSONObject subsidylist()throws Exception{
        JSONObject jsonObject=null;
        try {
            List<Subsidy> list=subsidyService.subsidylist();
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 助学金列表
     * @return
     * @throws Exception
     */
    @RequestMapping("/subsidylistforapplication")
    public JSONObject subsidylistforapplication()throws Exception{
        JSONObject jsonObject=null;
        try {
            List<Subsidy> list=subsidyService.subsidylistforapplication();
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 助学金申请列表
     * @param JSONobj
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/subsidyappList")
    public JSONObject subsidyappList(@RequestBody JSONObject JSONobj,HttpServletRequest httpServletRequest)throws Exception{
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");
        String tClassNum=null;
        if(user.getRoleIds().contains("4")){
            Teacher teacher=teacherService.selectClassNumByLoginName(user.getUsername());
            tClassNum=teacher.getTeaClassNum();
        }else if(user.getRoleIds().contains("9")){
            Student student=studentService.selectByStuUname(user.getUsername());
            tClassNum=student.getStuClass();
        }
        JSONObject jsonObject=null;
        String tTsuId=JSONobj.getString("tTsuId");
        try {
            List<SubsidyApplication> list=subsidyService.subsidyappList(tTsuId,tClassNum);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 助学金投票
     * @param subsidyApplication
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatesubsidyapplication")
    public JSONObject updatesubsidyapplication(@RequestBody SubsidyApplication subsidyApplication,HttpServletRequest httpServletRequest)throws Exception{
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");
        JSONObject jsonObject=null;

        try {
            subsidyApplication.setUserId(user.getUserid().toString());
            int resultNum=subsidyService.updateSubsidyApplication(subsidyApplication);
            if(resultNum>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,resultNum);
            }else {

                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"投票失败");
            }
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 助学金详情
     * @param JSONobj
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectSubsidy")
    public JSONObject selectSubsidy(@RequestBody JSONObject JSONobj)throws Exception{

        JSONObject jsonObject=null;
        String tsuId=JSONobj.getString("tsuId");

        try {
            Subsidy subsidy=subsidyService.selectSubsidy(tsuId);
            if(!ObjectUtils.isEmpty(subsidy)){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,subsidy);
            }else {

                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"查询失败");
            }
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 助学金获取名单
     * @param JSONobj
     * @return
     * @throws Exception
     */
    @RequestMapping("/subsidresultyList")
    public JSONObject subsidresultyList(@RequestBody JSONObject JSONobj)throws Exception{

        JSONObject jsonObject=null;
        String tsTsuId=JSONobj.getString("tsTsuId");
        try {
            List<SubsidyResult> list=subsidyService.subsidresultyList(tsTsuId);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 助学金申请
     * @param subsidyApplication
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/addSubsidyApplication")
    public JSONObject addSubsidyApplication(@RequestBody SubsidyApplication subsidyApplication,HttpServletRequest httpServletRequest)throws Exception{
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");
        Student student=new Student();
        if(user.getRoleIds().contains("9")){
            student=studentService.selectByStuUname(user.getUsername());
        }else{
            return ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"无权限");
        }
        subsidyApplication.setStuName(student.getStuName());
        subsidyApplication.settClassNum(student.getStuClass());
        subsidyApplication.settStuCardNum(student.getStuCardNum());
        subsidyApplication.setId(IDUtils.createID());
        JSONObject jsonObject=null;
        try {
            int resultNum =subsidyService.addSubsidyApplication(subsidyApplication);
            if(resultNum>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,resultNum);
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"申请失败");
            }

        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 助学金申请防止重复提交
     * @param subsidyApplication
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectBytTsuIdAndtStuCardNum")
    public JSONObject selectBytTsuIdAndtStuCardNum(@RequestBody SubsidyApplication subsidyApplication,HttpServletRequest httpServletRequest)throws Exception{
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");
        Student student=new Student();
        if(user.getRoleIds().contains("9")){
            student=studentService.selectByStuUname(user.getUsername());
        }else{
            return ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"无权限");
        }

        JSONObject jsonObject=null;
        try {
            boolean b =subsidyService.selectBytTsuIdAndtStuCardNum(subsidyApplication.gettTsuId(),student.getStuCardNum());
            if(b){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"1");
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"申请失败");
            }

        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 投票次数限制
     * @param studentSubsidy
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatesubsidyappilcationnum")
    public JSONObject touPiaoCiShu(@RequestBody StudentSubsidy studentSubsidy,HttpServletRequest httpServletRequest)throws Exception{
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");

        JSONObject jsonObject=null;
        try {
            boolean b =subsidyService.touPiaoCiShu(user.getUserid().toString(),studentSubsidy.gettSubsidyId());
            boolean c=subsidyService.touPiaoNum(studentSubsidy.gettStuCardNum(),user.getUserid().toString(),studentSubsidy.gettSubsidyId());
            if(b && c){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"1");
            }else if(b && c==false){
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_ONE,"您已经为他(她)投过了");
            }else if(c && b==false){
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_TWO,"每个人只能投三次");
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"查询失败");
            }

        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 助学金管理-列表
     * @param subsidy
     * @return
     * @throws Exception
     */
    @RequestMapping("/subsidylistforlist")
    public JSONObject subsidylistforlist(@RequestBody Subsidy subsidy)throws Exception{

        JSONObject jsonObject=null;
        try {
            List<Subsidy> list=subsidyService.subsidylistforlist(subsidy);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 添加
     * @param subsidy
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")
    public JSONObject add(@RequestBody Subsidy subsidy)throws Exception{
        JSONObject jsonObject=null;
        try {
            subsidy.setTsuId(IDUtils.createID());
            int num=subsidyService.add(subsidy);
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
     * 修改
     * @param subsidy
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public JSONObject update(@RequestBody Subsidy subsidy)throws Exception{
        JSONObject jsonObject=null;
        try {
            int num=subsidyService.update(subsidy);
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
     * 删除班级
     * @param JSONArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/deletesub")
    public JSONObject deletesub(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<Subsidy> list=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            Subsidy subsidy=new Subsidy();
            subsidy.setTsuId(JSONobj.getString("tsuId"));
            list.add(subsidy);
        }
        try {
            int resultNum=subsidyService.delete(list);
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
