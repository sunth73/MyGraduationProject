package com.wmk.porject.mapper.teachermapper;

import com.wmk.porject.bean.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(@Param("teaId") String teaId,@Param("teaLoginName")String teaLoginName);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String teaId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher>teaList(Teacher teacher);

    Teacher selectClassNumByLoginName(@Param("teaLoginName") String teaLoginName);

    Teacher selectTeacherByUserId(@Param("userid")Integer userid);

    List<Teacher>selectlistTea();

    Integer selectUserIdByTeaId(@Param("teaId")String teaId);
}