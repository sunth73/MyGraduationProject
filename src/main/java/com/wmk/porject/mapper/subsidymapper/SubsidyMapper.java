package com.wmk.porject.mapper.subsidymapper;

import com.wmk.porject.bean.Subsidy;

import java.util.List;

public interface SubsidyMapper {
    int deleteByPrimaryKey(String tsuId);

    int insert(Subsidy record);

    int insertSelective(Subsidy record);

    Subsidy selectByPrimaryKey(String tsuId);

    int updateByPrimaryKeySelective(Subsidy record);

    int updateByPrimaryKey(Subsidy record);

    List<Subsidy>subsidylist();
    List<Subsidy>subsidylistforapplication();
    List<Subsidy>subsidylistByState();

    List<Subsidy>subsidylistforlist(Subsidy subsidy);
}