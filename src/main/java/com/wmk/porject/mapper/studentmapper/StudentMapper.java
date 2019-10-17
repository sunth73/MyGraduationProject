package com.wmk.porject.mapper.studentmapper;

import com.wmk.porject.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(@Param("stuId") String stuId,@Param("stuUname") String stuUname);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String stuId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    /**
     * 学生列表查询
     * @param student
     * @return
     */
    List<Student> stuList(Student student);
    Student selectByStuUname(@Param("stuUname") String stuUname);

    List<String>selectByStuClass(@Param("stuClass") String stuClass);

    /**
     * 查询年级所有学生
     * @param gradeNum
     * @return
     */
    List<Student>studentListByGrade(@Param("gradeNum")String gradeNum);

    /**
     * 根据用户id查询学生信息
     * @param userid
     * @return
     */
    Student selectByUserId(@Param("userid")Integer userid);

}