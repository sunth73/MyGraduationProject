package com.wmk.porject.controller.tclasscontroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.TClass;
import com.wmk.porject.bean.Teacher;
import com.wmk.porject.service.classsevice.TClassService;
import com.wmk.porject.util.IDUtils;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-22 17:43
 * 描述
 */
@RestController
@RequestMapping("/tclass")
public class TClassController {
    @Autowired
    private TClassService tClassService;
    @RequestMapping("/classList")
    public JSONObject classList(@RequestBody TClass tClass)throws Exception{
        JSONObject jsonObject=null;
        try {
            List<TClass> teacherList=tClassService.classList(tClass.getTcGradeNum());
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,teacherList);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 添加班级
     * @param tClass
     * @return
     * @throws Exception
     */
    @RequestMapping("/addClass")
    public JSONObject addClass(@RequestBody TClass tClass)throws Exception{
        JSONObject jsonObject=null;
        try {
            tClass.setTcId(IDUtils.createID());
            int num=tClassService.addClass(tClass);
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
     * 修改班级
     * @param tClass
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateClass")
    public JSONObject updateClass(@RequestBody TClass tClass)throws Exception{
        JSONObject jsonObject=null;
        try {
            int num=tClassService.update(tClass);
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
    @RequestMapping("/deleteclass")
    public JSONObject deleteclass(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<TClass> classList=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            TClass tClass=new TClass();
            tClass.setTcId(JSONobj.getString("tcId"));
            classList.add(tClass);
        }
        try {
            int resultNum=tClassService.deleteclass(classList);
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

    /**
     * 年级列表
     * @param JSONobj
     * @return
     * @throws Exception
     */
    @RequestMapping("/gradeList")
    public JSONObject gradeList(@RequestBody JSONObject JSONobj)throws Exception{
        JSONObject jsonObject=null;
        try {
            List<TClass> teacherList=tClassService.gradeList();
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,teacherList);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 根据班级查年级
     * @param tClass
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectGradeNum")
    public JSONObject selectGradeNum(@RequestBody TClass tClass)throws Exception{
        JSONObject jsonObject=null;
        try {
            List<TClass> gradeNum=tClassService.selectGradeNum(tClass.getTcClassNum());
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,gradeNum);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
}
