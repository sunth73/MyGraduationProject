package com.wmk.porject.service.roleservice;

import com.alibaba.fastjson.JSON;
import com.wmk.porject.bean.Role;
import com.wmk.porject.bean.UserRole;
import com.wmk.porject.mapper.rolemapper.RoleMapper;
import com.wmk.porject.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-09 21:43
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询当前登录人菜单集合
     * @param roleid
     * @return
     */
    public String selectAuthIds(Integer  roleid){
        return roleMapper.selsctAuthIds(roleid);
    }
    /**
     * 查询当前登录人菜单集合
     * @param list
     * @return
     */
    public String selectAuthIds(List<UserRole> list){
        List<String>list1=new ArrayList<>();
        for(UserRole userRole:list){
            String authids=roleMapper.selsctAuthIds(userRole.getRoleId());
            if (!StringUtils.isEmpty(authids)){
                list1.add(authids);
            }
        }
        return StringUtil.merge(list1);
    }

    /**
     * 查询角色列表
     * @return
     * @throws Exception
     */
    public List<Role> roleList(Role role)throws Exception{
        return roleMapper.roleList(role);
    }

    /**
     * 添加角色
     * @param role
     * @return
     * @throws Exception
     */
    public int addRole(Role role)throws Exception{
        return roleMapper.insertSelective(role);
    }

    /**
     * 修改角色
     * @param role
     * @return
     * @throws Exception
     */
    public int updateRole(Role role)throws Exception{
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 删除角色
     * @param roleidList
     * @return
     * @throws Exception
     */
    public int deleteRole(List<Integer> roleidList)throws Exception{
        int num=0;
        if(!CollectionUtils.isEmpty(roleidList)){
            for(Integer roleId:roleidList){
                roleMapper.deleteByPrimaryKey(roleId);
                num++;
            }
        }
        return num;
    }
}
