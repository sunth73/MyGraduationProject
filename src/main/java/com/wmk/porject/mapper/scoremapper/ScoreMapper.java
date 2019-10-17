package com.wmk.porject.mapper.scoremapper;

import com.wmk.porject.bean.Score;
import com.wmk.porject.request.ScoreReq;
import com.wmk.porject.request.ScoreRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScoreMapper {
    int deleteByPrimaryKey(String recId);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(String recId);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

    /**
     * 成绩列表
     * @param paramMap
     * @return
     */
    List<ScoreRequest>selectScoreForClass(Map<String, Object> paramMap);

    /**
     * 单科成绩
     * @param scoreReq
     * @return
     */
    List<ScoreReq>scorelist(ScoreReq scoreReq);

    /**
     * 全科成绩
     * @param stuClass
     * @param sExamId
     * @return
     */
    List<ScoreRequest>selectScorelist(@Param("stuClass")String stuClass,@Param("sExamId")String sExamId);
}