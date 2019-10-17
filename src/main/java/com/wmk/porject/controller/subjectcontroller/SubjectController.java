package com.wmk.porject.controller.subjectcontroller;

import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Exam;
import com.wmk.porject.bean.Subject;
import com.wmk.porject.service.subjectservice.SubjectService;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-12 18:13
 * 描述
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 科目列表
     * @param subject
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public JSONObject list(@RequestBody Subject subject)throws Exception{
        List<Subject> list= null;
        JSONObject jsonObject=null;
        try {
            list = subjectService.sublict();
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
}
