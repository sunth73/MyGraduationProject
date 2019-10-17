package com.wmk.porject.service.userservice;

import com.wmk.porject.bean.TClass;
import com.wmk.porject.bean.User;
import com.wmk.porject.bean.UserRole;
import com.wmk.porject.mapper.classmapper.TClassMapper;
import com.wmk.porject.mapper.usermapper.UserMapper;
import com.wmk.porject.mapper.userrolemapper.UserRoleMapper;
import com.wmk.porject.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-09 12:36
 * 描述
 */
@Service//service层注入
@Transactional(rollbackFor = Exception.class)//事务开启
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TClassMapper tClassMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    /**
     * 登录查询
     * */
    public User selectForLogin(String loginName)throws Exception{
        User user=userMapper.selectForLogin(loginName);
        return user;
    }

    /**
     * 用户详细信息
     * @param userid
     * @return
     * @throws Exception
     */
    public User userInformation(Integer userid)throws Exception{
        User user=userMapper.userInformation(userid);
        return user;
    }
    /**
     * 查询用户列表
     * */
    public List<User>userList(User user)throws Exception{
        return userMapper.userList(user);
    }

    /**
     * 用户添加
     * @param user
     * @return
     * @throws Exception
     */
    public int adduser(User user)throws Exception{
        int num= userMapper.insertSelective(user);
        if(num==1){
            User user1=userMapper.selectByUserName(user.getUsername());
            String[] roleids=user.getRoleIds().split(",");
            for(int i=0;i<roleids.length;i++){
                UserRole userRole=new UserRole();
                userRole.setRec(IDUtils.createID());
                userRole.setUserId(user1.getUserid());
                userRole.setRoleId(Integer.parseInt(roleids[i]));
                userRoleMapper.insertSelective(userRole);
            }
        }

        return num;
    }

    /**
     * 用户修改
     * @param user
     * @return
     * @throws Exception
     */
    public int updateUser(User user)throws Exception{
        userRoleMapper.deleteByUserId(user.getUserid());
        String[] roleids=user.getRoleIds().split(",");
        for(int i=0;i<roleids.length;i++){
            UserRole userRole=new UserRole();
            userRole.setRec(IDUtils.createID());
            userRole.setUserId(user.getUserid());
            userRole.setRoleId(Integer.parseInt(roleids[i]));
            userRoleMapper.insertSelective(userRole);
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     *
     * @param useridList
     * @return
     * @throws Exception
     */
    public int deleteUser(List<User> useridList)throws Exception{
        int resultNum=0;
        if(!CollectionUtils.isEmpty(useridList)){
            for (User user:useridList){
                if(user.getRoleIds().contains("9")){
                    userMapper.deleteforUserStu(user.getUsername(),user.getUserid());
                }else if(user.getRoleIds().contains("4")){
                    userMapper.deleteforUserTea(user.getUsername(),user.getUserid());
                    tClassMapper.updateByUserName(user.getUsername());
                } else{
                    userMapper.deleteByPrimaryKey(user.getUserid());
                }
                resultNum++;
            }
        }
        return resultNum;
    }
}
