package com.wmk.porject.mapper.newsmapper;

import com.wmk.porject.bean.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(String id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    int newsnum(@Param("receiveId") Integer receiveId);

    List<News>newslist(News news);

    List<News>newslisttwo(News news);
}