package com.wmk.porject.mapper.dormscoremapper;

import com.wmk.porject.bean.DormScore;

public interface DormScoreMapper {
    int deleteByPrimaryKey(String tdsId);

    int insert(DormScore record);

    int insertSelective(DormScore record);

    DormScore selectByPrimaryKey(String tdsId);

    int updateByPrimaryKeySelective(DormScore record);

    int updateByPrimaryKey(DormScore record);
}