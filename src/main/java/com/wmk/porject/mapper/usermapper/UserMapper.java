package com.wmk.porject.mapper.usermapper;

import com.wmk.porject.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateByEmpname(User user);

    User selectForLogin(@Param("loginName") String loginName);

    List<User> userList(User user);

    List<String>selectUserName(@Param("username")String username);

    int deleteforUserStu(@Param("stuUname") String stuUname,@Param("userid") Integer userid);

    int deleteforUserTea(@Param("teaLoginName") String teaLoginName,@Param("userid") Integer userid);

    User selectByUserName(@Param("username")String username);

    User userInformation(@Param("userid")Integer userid);
}