package com.wmk.porject.mapper.subsidyresult;

import com.wmk.porject.bean.SubsidyResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubsidyResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubsidyResult record);

    int insertSelective(SubsidyResult record);

    SubsidyResult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubsidyResult record);

    int updateByPrimaryKey(SubsidyResult record);

    List<SubsidyResult>subsidresultyList(@Param("tsTsuId") String tsTsuId);
}