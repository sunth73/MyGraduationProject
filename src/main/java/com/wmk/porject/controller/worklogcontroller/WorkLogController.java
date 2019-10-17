package com.wmk.porject.controller.worklogcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.News;
import com.wmk.porject.bean.User;
import com.wmk.porject.bean.WorkLog;
import com.wmk.porject.service.worklogservice.WorkLogService;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-21 20:46
 * 描述
 */
@RestController
@RequestMapping("/log")
public class WorkLogController {
    @Autowired
    private WorkLogService workLogService;
    @RequestMapping("/list")
    public JSONObject list(@RequestBody WorkLog workLog, HttpServletRequest httpServletRequest)throws Exception{
        User user=(User)httpServletRequest.getSession().getAttribute("LoginUser");
        List<WorkLog> list= null;
        JSONObject jsonObject=null;
        try {

            list = workLogService.logList(workLog);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 查询留言详细信息
     * @param workLog
     * @return
     * @throws Exception
     */
    @RequestMapping("/select")
    public JSONObject select(@RequestBody WorkLog workLog)throws Exception{
        JSONObject jsonObject=null;
        WorkLog workLog1=workLogService.selectworkLog(workLog.gettId());
        if(!ObjectUtils.isEmpty(workLog1)&&workLog1!=null){
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,workLog1);
        }else {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"查询失败");
        }
        return jsonObject;
    }

    /**
     * 保存日志
     * @param workLog
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public JSONObject update(@RequestBody WorkLog workLog)throws Exception{
        JSONObject jsonObject=null;
        if(workLog.getLogFlag()=="1"){
            workLog.setLogFlag("2");
        }else{
            workLog.setLogFlag("1");
        }

        String js= JSON.toJSONString(workLog);
        System.out.println("======"+js);
        int num=workLogService.update(workLog);
        if(num>0){
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,num);
        }else {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"保存失败");
        }
        return jsonObject;
    }

    /**
     * 一键提交
     * @param workLog
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateall")
    public JSONObject updateall(@RequestBody WorkLog workLog)throws Exception{
        JSONObject jsonObject=null;
        workLog.setLogFlag("1");
        try {
            List<WorkLog> list = workLogService.logList(workLog);
            int num=0;
            for(WorkLog workLog1:list){
                WorkLog workLog2=new WorkLog();
                workLog2.settId(workLog1.gettId());
                workLog2.setLogFlag("2");
                workLogService.update(workLog2);
                num++;
            }
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,num);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"保存失败");
            e.printStackTrace();
        }
        return jsonObject;
    }
}
