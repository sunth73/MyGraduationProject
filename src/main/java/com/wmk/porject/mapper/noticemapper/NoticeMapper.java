package com.wmk.porject.mapper.noticemapper;

import com.wmk.porject.bean.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice>noticeList();

    List<Notice>selectByCreateor(Notice notice);
}