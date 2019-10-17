package com.wmk.porject.mapper.dormmapper;

import com.wmk.porject.bean.Dorm;
import com.wmk.porject.request.DormScoreRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DormMapper {
    int deleteByPrimaryKey(String dormId);

    int insert(Dorm record);

    int insertSelective(Dorm record);

    Dorm selectByPrimaryKey(String dormId);

    int updateByPrimaryKeySelective(Dorm record);

    int updateByPrimaryKey(Dorm record);

    /**
     * 宿舍管理员查询
     * @param dormScoreRequest
     * @return
     */
    List<DormScoreRequest>selectDormByAdmin(DormScoreRequest dormScoreRequest);

    /**
     * 班主任查看
     * @param dormScoreRequest
     * @return
     */
    List<DormScoreRequest>selectDormByTeacher(DormScoreRequest dormScoreRequest);

    /**
     * 学生查看
     * @param dormScoreRequest
     * @return
     */
    List<DormScoreRequest>selectDormByStudent(DormScoreRequest dormScoreRequest);

    /**
     *查询
     * @param tdsId
     * @return
     */
    DormScoreRequest selectScore(@Param("tdsId") String tdsId);

    /**
     * 宿舍列表
     * @return
     */
    List<Dorm>dromList();

    List<Dorm>dromList2();
}