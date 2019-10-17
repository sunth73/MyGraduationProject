package com.wmk.porject.controller.authcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Auth;
import com.wmk.porject.bean.User;
import com.wmk.porject.bean.UserRole;
import com.wmk.porject.mapper.authmapper.AuthMapper;
import com.wmk.porject.mapper.rolemapper.RoleMapper;
import com.wmk.porject.service.authService.AuthService;
import com.wmk.porject.service.roleservice.RoleService;
import com.wmk.porject.service.userroleservice.UserRoleService;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-09 14:19
 * 描述
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 初始化获取当前登录人菜单列表
     * @param JSONobj
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/getmenu")
    public JSONArray menu(@RequestBody JSONObject JSONobj,HttpServletRequest httpServletRequest)throws Exception{
        String parentid=JSONobj.getString("parentid");
        User user= (User) httpServletRequest.getSession().getAttribute("LoginUser");
        Integer roleid=null;
        List<UserRole> list=null;
        if(!ObjectUtils.isEmpty(user)){
            list =userRoleService.listByUserId(user.getUserid());
        }
        String AuthIds=roleService.selectAuthIds(list);
        JSONArray jsonArray =authService.getAuthsByParentId("-1", AuthIds);
        return jsonArray;
    }

    /**
     * 角色授权获取菜单列表
     * @param JSONobj
     * @return
     * @throws Exception
     */
    @RequestMapping("/authMenu")
    public JSONArray getCheckedAuthByParentId(@RequestBody JSONObject JSONobj)throws Exception{
        String parentid=JSONobj.getString("parentid");
        Integer roleid=JSONobj.getInteger("roleid");
        System.out.println("========="+parentid+"   "+roleid);
        String AuthIds=roleService.selectAuthIds(roleid);
        JSONArray jsonArray=authService.getCheckedAuthsByParentId(parentid,AuthIds);
        return jsonArray;
    }

    /**
     * 菜单管理菜单列表
     * @param JSONobj
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAuthMenu")
    public JSONArray authTreeGridMenuAction(@RequestBody JSONObject JSONobj)throws Exception{
        String parentid=JSONobj.getString("parentid");
        JSONArray jsonArray=authService.getListByParentId(parentid);
        return jsonArray;
    }

    /**
     * 添加菜单
     * @param auth
     * @return
     * @throws Exception
     */
    @RequestMapping("/addauth")
    public JSONObject addAuth(@RequestBody Auth auth)throws Exception{
        JSONObject jsonObject=null;
        auth.setAuthid(null);
        auth.setState("open");
        try {
            int resultNum=authService.addMenu(auth);
            if(resultNum>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"菜单添加成功");
            }else{
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"菜单添加失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"菜单添加异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 修改菜单
     * @param auth
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateauth")
    public JSONObject updateAuth(@RequestBody Auth auth)throws Exception{
        JSONObject jsonObject=null;
        try {
            int resultNum=authService.updateMenu(auth);
            if(resultNum>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"菜单修改成功");
            }else{
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"菜单修改失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"菜单修改异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 查询要删除的节点是否为叶子节点
     * @param auth
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectChildren")
    public JSONObject selectNodes(@RequestBody Auth auth)throws Exception{
        JSONObject jsonObject=null;
        try {
            List<Auth>list=authService.selectByParentId(auth.getParentid());
            if(list.size()==0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"ok");
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"存在子节点无法删除");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * 删除菜单
     * @param auth
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteauth")
    public JSONObject deleteMenu(@RequestBody Auth auth)throws Exception{
        JSONObject jsonObject=null;
        try {
            int resultNum=authService.deleteAuth(auth);
            if(resultNum>0){
                jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"删除成功");
            }else {
                jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"删除失败");
            }
        } catch (Exception e) {
            jsonObject=ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"删除异常");
            e.printStackTrace();
        }
        return  jsonObject;
    }
    @RequestMapping("/importUser")
    public JSONObject ImportUser(@RequestPart("file")MultipartFile file)throws Exception{
        JSONObject jsonObject=null;
        System.out.println("===="+file.getOriginalFilename());
        return ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"哈哈哈哈");
    }
}
