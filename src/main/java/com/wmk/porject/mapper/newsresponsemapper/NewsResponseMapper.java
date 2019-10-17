package com.wmk.porject.mapper.newsresponsemapper;

import com.wmk.porject.bean.NewsResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsResponseMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsResponse record);

    int insertSelective(NewsResponse record);

    NewsResponse selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsResponse record);

    int updateByPrimaryKey(NewsResponse record);

    List<NewsResponse>selectByNewsId(@Param("newsId") String newsId);
}