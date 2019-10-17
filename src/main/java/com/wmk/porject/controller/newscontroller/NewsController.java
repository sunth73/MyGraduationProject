package com.wmk.porject.controller.newscontroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wmk.porject.bean.News;
import com.wmk.porject.bean.User;
import com.wmk.porject.service.newsservice.NewsService;
import com.wmk.porject.util.IDUtils;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-06 16:35
 * 描述
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 未读消息数量
     * @param JSONObj
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/newsnum")
    public JSONObject num(@RequestBody JSONObject JSONObj, HttpServletRequest httpServletRequest)throws Exception{
        User user=(User)httpServletRequest.getSession().getAttribute("LoginUser");
        JSONObject jsonObject=null;
        try {
            int num=newsService.newsnum(user.getUserid());
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,num);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 留言列表
     * @param news
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public JSONObject list(@RequestBody News news,HttpServletRequest httpServletRequest)throws Exception{
        User user=(User)httpServletRequest.getSession().getAttribute("LoginUser");
        List<News> list= null;
        JSONObject jsonObject=null;
        try {
            news.setReceiveId(user.getUserid());
            list = newsService.list(news);
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 留言列表
     * @param news
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/listtwo")
    public JSONObject listtwo(@RequestBody News news,HttpServletRequest httpServletRequest)throws Exception{
        User user=(User)httpServletRequest.getSession().getAttribute("LoginUser");
        List<News> list= null;
        JSONObject jsonObject=null;
        try {
            news.setSendId(user.getUserid());
            list = newsService.listtwo(news);
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 查询留言详细信息
     * @param news
     * @return
     * @throws Exception
     */
    @RequestMapping("/select")
    public JSONObject select(@RequestBody News news)throws Exception{
        News news1=newsService.select(news.getId());
        JSONObject jsonObject=null;
        if(!ObjectUtils.isEmpty(news1)&&news1!=null){
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,news1);
        }else {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"查询失败");
        }
        return jsonObject;
    }
    /**
     * 发送者查询留言详细信息
     * @param news
     * @return
     * @throws Exception
     */
    @RequestMapping("/selecttwo")
    public JSONObject selecttwo(@RequestBody News news)throws Exception{
        News news1=newsService.selecttwo(news.getId());
        JSONObject jsonObject=null;
        if(!ObjectUtils.isEmpty(news1)&&news1!=null){
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,news1);
        }else {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"查询失败");
        }
        return jsonObject;
    }
    /**
     * 留言列表
     * @param news
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/newsList")
    public JSONObject newsList(@RequestBody News news, HttpServletRequest httpServletRequest)throws Exception{
        User user=(User)httpServletRequest.getSession().getAttribute("LoginUser");
        List<News> list= null;
        JSONObject jsonObject=null;
        try {
            news.setReceiveId(user.getUserid());
            list = newsService.newsList(news);
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"list",list);
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 添加留言
     * @param news
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")
    public JSONObject add(@RequestBody News news,HttpServletRequest httpServletRequest)throws Exception{
        User user=(User)httpServletRequest.getSession().getAttribute("LoginUser");
        news.setIsShow("1");
        news.setId(IDUtils.createID());
        news.settDate(new Date());
        news.setSendId(user.getUserid());
        JSONObject jsonObject=null;
        try {
            int num=newsService.add(news);
            if(num>0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"添加成功");
            }else{
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"添加失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"添加异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 删除留言
     * @param JSONArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public JSONObject deleteStu(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<News> newsList=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            News news=new News();
            news.setId(JSONobj.getString("id"));
            newsList.add(news);
        }
        try {
            int resultNum=newsService.delete(newsList);
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
