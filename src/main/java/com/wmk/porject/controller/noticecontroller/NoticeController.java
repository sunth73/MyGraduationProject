package com.wmk.porject.controller.noticecontroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Notice;
import com.wmk.porject.bean.Student;
import com.wmk.porject.bean.User;
import com.wmk.porject.service.noticeservice.NoticeService;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-05 22:05
 * 描述
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @RequestMapping("/add")
    public JSONObject add(@RequestBody Notice notice)throws Exception{
        int num=noticeService.addNotic(notice);
        JSONObject jsonObject=null;
        if(num>0){
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"添加成功");
        }else{
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"添加失败");
        }
        return jsonObject;
    }

    /**
     * 公告列表
     * @param JSONobj
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public JSONObject list(@RequestBody JSONObject JSONobj)throws Exception{
        List<Notice>list= null;
        JSONObject jsonObject=null;
        try {
            list = noticeService.list();
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    @RequestMapping("/select")
    public JSONObject select(@RequestBody Notice notice)throws Exception{
        Notice notice1=noticeService.select(notice.getId());
        JSONObject jsonObject=null;
        if(!ObjectUtils.isEmpty(notice1)&&notice1!=null){
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,notice1);
        }else {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"查询失败");
        }
        return jsonObject;
    }

    /**
     * 个人公告管理
     * @param JSONobj
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/listforcreator")
    public JSONObject listforcreator(@RequestBody JSONObject JSONobj, HttpServletRequest httpServletRequest)throws Exception{
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");
        List<Notice>list= null;
        JSONObject jsonObject=null;
        try {
            Notice notice=new Notice();
            if(user.getUserid()!=1){
                notice.setCreator(user.getUserid());
            }
            notice.setTitle(JSONobj.getString("title"));
            list = noticeService.selectByCreateor(notice);
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
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
    public JSONObject delete(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
            List<Notice> list=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            Notice notice=new Notice();
            notice.setId(JSONobj.getString("id"));
            list.add(notice);
        }
        try {
            int resultNum=noticeService.delete(list);
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
