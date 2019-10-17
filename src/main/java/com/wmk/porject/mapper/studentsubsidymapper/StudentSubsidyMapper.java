package com.wmk.porject.mapper.studentsubsidymapper;

import com.wmk.porject.bean.StudentSubsidy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentSubsidyMapper {
    int deleteByPrimaryKey(String recId);

    int insert(StudentSubsidy record);

    int insertSelective(StudentSubsidy record);

    StudentSubsidy selectByPrimaryKey(String recId);

    int updateByPrimaryKeySelective(StudentSubsidy record);

    int updateByPrimaryKey(StudentSubsidy record);

    List<StudentSubsidy>stusubList(@Param("tStuCardNum") String tStuCardNum,@Param("tUserId") String tUserId,@Param("tSubsidyId") String tSubsidyId);
}