package com.wmk.porject.mapper.subsidyapplicationmapper;

import com.wmk.porject.bean.SubsidyApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubsidyApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubsidyApplication record);

    int insertSelective(SubsidyApplication record);

    SubsidyApplication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubsidyApplication record);

    int updateByPrimaryKey(SubsidyApplication record);

    List<SubsidyApplication>subsidyappList(@Param("tTsuId") String tTsuId,@Param("tClassNum")String tClassNum);

    List<SubsidyApplication> selectBytTsuIdAndtStuCardNum(@Param("tTsuId") String tTsuId,@Param("tStuCardNum") String tStuCardNum);
    List<SubsidyApplication>newlist(@Param("tTsuId") String tTsuId,@Param("num")String num);
}