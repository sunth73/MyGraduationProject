package com.wmk.porject.service.authService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Auth;
import com.wmk.porject.mapper.authmapper.AuthMapper;
import com.wmk.porject.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sunth
 * @DateTime 2019-04-09 21:03
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthService {
    @Autowired
    private AuthMapper authMapper;

    /**
     * 初始化菜单查询
     * @param parentId
     * @param authIds
     * @return
     * @throws Exception
     */
    public JSONArray getAuthsByParentId(String parentId, String authIds)throws Exception{
        JSONArray jsonArray=this.getAuthByParentId(parentId,authIds);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            if("open".equals(jsonObject.getString("state"))){
                continue;
            }else{
                String js=JSON.toJSONString(jsonObject);
                jsonObject.put("children", getAuthsByParentId(jsonObject.getString("id"),authIds));
            }
        }
        return jsonArray;
    }
    public JSONArray getAuthByParentId(String parentId,String authIds)throws Exception{
        JSONArray jsonArray=new JSONArray();
        Map<String, Object> paramMap = new HashMap<>();
        String[] authids=null;
        if(authIds!=null){
            authids=authIds.split(",");
        }
        List<String>list=new ArrayList<>();
        for (int i=0;i<authids.length;i++){
            list.add(authids[i]);
        }
        paramMap.put("list",list);
        paramMap.put("authid",parentId);
        List<Auth>authList=authMapper.getAuthByParentId(paramMap);
        String js= JSON.toJSONString(authList);
        for(Auth auth:authList){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id", auth.getAuthid());
            jsonObject.put("text", auth.getAuthname());
            if(!hasChildren(auth.getAuthid().toString(), authIds)){
                jsonObject.put("state", "open");
            }else{
                jsonObject.put("state", auth.getState());
            }
            jsonObject.put("iconCls", auth.getIconcls());
            JSONObject attributeObject=new JSONObject();
            attributeObject.put("authPath", auth.getAuthpath());
            jsonObject.put("attributes", attributeObject);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    private boolean hasChildren(String parentId,String authIds)throws Exception{
        Map<String, Object> paramMap = new HashMap<>();
        String[] authids=authIds.split(",");
        List<String>list=new ArrayList<>();
        for (int i=0;i<authids.length;i++){
            list.add(authids[i]);
        }
        paramMap.put("list",list);
        paramMap.put("authid",parentId);
        List<Auth>authList=authMapper.getAuthByParentId(paramMap);
        if(CollectionUtils.isEmpty(authList)){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 授权查询
     * @param parentId
     * @param authIds
     * @return
     * @throws Exception
     */
    public JSONArray getCheckedAuthsByParentId(String parentId,String authIds)throws Exception{
        JSONArray jsonArray=this.getCheckedAuthByParentId( parentId,authIds);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            if("open".equals(jsonObject.getString("state"))){
                continue;
            }else{
                jsonObject.put("children", getCheckedAuthsByParentId(jsonObject.getString("id"),authIds));
            }
        }
        return jsonArray;
    }
    public JSONArray getCheckedAuthByParentId(String parentId,String authIds)throws Exception{
        JSONArray jsonArray=new JSONArray();
        List<Auth>list=authMapper.selectByParentId(Integer.parseInt(parentId));
       for(Auth auth:list){
           JSONObject jsonObject=new JSONObject();
           jsonObject.put("id",auth.getAuthid());
           jsonObject.put("text", auth.getAuthname());
           jsonObject.put("state", auth.getState());
           jsonObject.put("iconCls", auth.getIconcls());
           if(StringUtil.existStrArr(auth.getAuthid()+"", authIds.split(","))){
               jsonObject.put("checked", true);
           }
           JSONObject attributeObject=new JSONObject();
           attributeObject.put("authPath", auth.getAuthpath());
           jsonObject.put("attributes", attributeObject);
           jsonArray.add(jsonObject);
       }
        return jsonArray;
    }

    /**
     * 菜单列表查询
     * @param parentId
     * @return
     * @throws Exception
     */
    public JSONArray getListByParentId(String parentId)throws Exception {
        JSONArray jsonArray = this.getTreeGridAuthByParentId(parentId);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if ("open".equals(jsonObject.getString("state"))) {
                continue;
            } else {
                jsonObject.put("children", getListByParentId(jsonObject.getString("id")));
            }
        }
        return jsonArray;
    }
    public JSONArray getTreeGridAuthByParentId(String parentId)throws Exception{
        JSONArray jsonArray=new JSONArray();
        List<Auth>authList=authMapper.selectByParentId(Integer.parseInt(parentId));
        for (Auth auth:authList){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id", auth.getAuthid());
            jsonObject.put("text", auth.getAuthname());
            jsonObject.put("state", auth.getState());
            jsonObject.put("iconCls", auth.getIconcls());
            jsonObject.put("authPath", auth.getAuthpath());
            jsonObject.put("authDescription",auth.getAuthdescription());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * 菜单添加
     * @param auth
     * @return
     * @throws Exception
     */
    public int addMenu(Auth auth)throws Exception{
        Auth auth1=new Auth();
        auth1.setAuthid(auth.getParentid());
        auth1.setState("closed");
        int resultNum= authMapper.updateByPrimaryKeySelective(auth1);
        int addNum=0;
        if(resultNum>0){
            addNum= authMapper.insertSelective(auth);
        }
        return addNum;
    }

    /**
     * 菜单修改
     * @param auth
     * @return
     * @throws Exception
     */
    public int updateMenu(Auth auth)throws Exception{
        return authMapper.updateByPrimaryKeySelective(auth);
    }

    /**
     * 查询要删除的菜单是否存在子菜单
     * @param parentid
     * @return
     * @throws Exception
     */
    public List<Auth> selectByParentId(Integer parentid)throws Exception{
        return authMapper.selectByParentId(parentid);
    }

    /**
     * 删除菜单
     * @param auth
     * @return
     * @throws Exception
     */
    public int deleteAuth( Auth auth)throws Exception{
        Auth auth1=new Auth();
        auth1.setAuthid(auth.getParentid());
        auth1.setState("closed");
        int updateNum= authMapper.updateByPrimaryKeySelective(auth1);
        int deleteNum=0;
        if(updateNum>0){
            deleteNum=authMapper.deleteByPrimaryKey(auth.getAuthid());
        }
        return deleteNum;
    }
}
