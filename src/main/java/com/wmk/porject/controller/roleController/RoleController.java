package com.wmk.porject.controller.roleController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Auth;
import com.wmk.porject.bean.Role;
import com.wmk.porject.service.roleservice.RoleService;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-10 21:50
 * 描述
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表查询
     * @return
     * @throws Exception
     */
    @RequestMapping("/rolelist")
    public JSONObject roleList(@RequestBody JSONObject JSONobj)throws Exception{
        String roleName=JSONobj.getString("rolename");
        Role role=new Role();
        if(!StringUtils.isEmpty(roleName)){
            role.setRolename(roleName);
        }
        JSONObject jsonObject=null;
        try {
            List<Role>list=roleService.roleList(role);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 添加角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/addrole")
    public JSONObject addRole(@RequestBody Role role)throws Exception{
        JSONObject jsonObject=null;
        try {
            role.setRoleid(null);
            int num= roleService.addRole(role);
            if(num>0){
               jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"添加角色成功");
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"添加角色失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"添加角色异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 添加角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/updaterole")
    public JSONObject updateRole(@RequestBody Role role)throws Exception{
        JSONObject jsonObject=null;
        try {
            role.setAuthids("");
            int resultNum= roleService.updateRole(role);
            if(resultNum>0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"修改角色成功");
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"修改角色失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"修改角色异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    @RequestMapping("/deleterole")
    public JSONObject deleteRole(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<Integer> roleIdList=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            roleIdList.add(JSONobj.getInteger("roleid"));
        }
        try {
            int resultNum=roleService.deleteRole(roleIdList);
            if(resultNum>0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"删除成功");
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"删除失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"删除异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 授权
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/authorize")
    public JSONObject Authorize(@RequestBody Role role)throws Exception{
        JSONObject jsonObject=null;
        try {
            int resultNum= roleService.updateRole(role);
            if(resultNum>0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"角色授权成功");
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"角色授权失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"角色授权异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
}
