package com.wmk.porject.controller.usercontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.User;
import com.wmk.porject.bean.UserRole;
import com.wmk.porject.service.studentservice.StudentService;
import com.wmk.porject.service.userroleservice.UserRoleService;
import com.wmk.porject.service.userservice.UserService;
import com.wmk.porject.util.CodeConstans;
import com.wmk.porject.util.IDUtils;
import com.wmk.porject.util.MD5Util;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-09 12:14
 * 描述
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;


    /**
     * 用户列表查询
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/userlist")
    public JSONObject userList(@RequestBody User user)throws Exception{
        JSONObject jsonObject=null;
        List<User>list= null;
        try {
            list = userService.userList(user);
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new Exception(ReturnUtil.CODE_FAIL + CodeConstans.DEFAULT_SPLIT_STRING +"数据查询异常");
        }
        return jsonObject;
    }

    /**
     * 用户添加
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/adduser")
    public JSONObject adduser(@RequestBody User user)throws Exception{
        JSONObject jsonObject=null;
        user.setPassword("123456");
        user.setUserid(null);

        System.out.println("=========>>>>>>>>"+JSON.toJSONString(user));
        try {
            int num=userService.adduser(user);
            if(num>0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,num);
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"添加失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"添加异常");
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * 插询用户名
     * @param JSONobj
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectusername")
    public JSONObject adduser(@RequestBody JSONObject JSONobj)throws Exception{
        JSONObject jsonObject=null;
        String name=JSONobj.getString("empname");
        try {
            String userName=studentService.getUserName(name);
            if(userName!=null&&!"".equals(userName)){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,userName);
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"查询失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }

        return jsonObject;
    }


    /**
     * 用户修改
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateuser")
    public JSONObject updateUser(@RequestBody User user)throws Exception{
        JSONObject jsonObject=null;
        try {
            int num=userService.updateUser(user);
            if(num>0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"修改成功",num);
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"修改失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"修改异常");
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * 用户删除
     * @param JSONArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteuser")
    public JSONObject deleteUser(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<User> uIdList=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            User user=new User();
            user.setUserid(JSONobj.getInteger("userid"));
            user.setUsername(JSONobj.getString("username"));
            user.setRoleid(JSONobj.getInteger("roleid"));
            uIdList.add(user);
        }
        try {
            int resutNum=userService.deleteUser(uIdList);
            if(resutNum>0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"用户删除成功",resutNum);
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"用户删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 密码修改
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatePwd")
    public JSONObject updatePwd(@RequestBody User user)throws Exception{
        JSONObject jsonObject=null;
        try {
            int num=userService.updateUser(user);
            if(num==-1){
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"原密码错误");
            }else if(num==0){
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"修改失败，请重试");
            }else if(num>0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"修改成功",num);
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"修改异常");
            e.printStackTrace();
        }

        return jsonObject;
    }
}
