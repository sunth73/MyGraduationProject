package com.wmk.porject.service.userroleservice;

import com.wmk.porject.bean.UserRole;
import com.wmk.porject.mapper.userrolemapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-25 9:57
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<UserRole> listByUserId(Integer userId)throws Exception{
        return userRoleMapper.listByUserId(userId);
    }
    public int insertSelective(UserRole record)throws Exception{
        return userRoleMapper.insertSelective(record);
    }
}
