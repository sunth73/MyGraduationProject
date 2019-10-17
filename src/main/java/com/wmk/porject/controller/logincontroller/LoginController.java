package com.wmk.porject.controller.logincontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.User;
import com.wmk.porject.service.userservice.UserService;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Sunth
 * @DateTime 2019-04-08 17:45
 * 描述
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public JSONObject login(@RequestBody User user, HttpSession session)throws Exception{
        JSONObject jsonObject=null;
        if(StringUtils.isEmpty(user.getUsername())||StringUtils.isEmpty(user.getPassword())){
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"用户名或密码不能为空");
        }
        User uu=userService.selectForLogin(user.getUsername());
        if(ObjectUtils.isEmpty(uu)){
            return ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"用户不存在");
        }
        System.out.println("========="+user.getPassword()+"                 "+uu.getPassword());
        if(user.getPassword().equals(uu.getPassword())||uu.getPassword().equals(user.getPassword())){
            User u=userService.userInformation(uu.getUserid());
            session.setAttribute("LoginUser",u);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,u);
        }else{
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"用户名或密码错误");
        }
       return jsonObject;
    }
}
