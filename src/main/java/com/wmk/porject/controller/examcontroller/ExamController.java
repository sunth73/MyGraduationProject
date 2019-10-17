package com.wmk.porject.controller.examcontroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Dorm;
import com.wmk.porject.bean.Exam;
import com.wmk.porject.service.examservice.ExamService;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-12 10:33
 * 描述
 */
@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    /**
     * 考试列表
     * @param exam
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public JSONObject list(@RequestBody Exam exam)throws Exception{
        List<Exam> list= null;
        JSONObject jsonObject=null;
        try {
            list = examService.examList(exam);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 添加考试
     * @param exam
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")
    public JSONObject add(@RequestBody Exam exam)throws Exception{
        int num=examService.addExam(exam);
        JSONObject jsonObject=null;
        if(num>0){
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"添加成功");
        }else{
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"添加失败");
        }
        return jsonObject;
    }

    /**
     * 修改考试内容
     * @param exam
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public JSONObject updateStu(@RequestBody Exam exam)throws Exception{
        JSONObject jsonObject=null;
        try {
            int resultNum=examService.updateExam(exam);
            if(resultNum>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"修改成功");
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"修改失败");
            }
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"修改异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 删除
     * @param JSONArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public JSONObject deleteclass(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<Exam> list=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            System.out.println("=========="+JSONobj.toString());
            Exam exam=new Exam();
            exam.seteId(JSONobj.getString("eId"));
            list.add(exam);
        }
        try {
            int resultNum=examService.delete(list);
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
