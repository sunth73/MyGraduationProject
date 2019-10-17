package com.wmk.porject.service.noticeservice;

import com.wmk.porject.bean.Notice;
import com.wmk.porject.mapper.noticemapper.NoticeMapper;
import com.wmk.porject.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-05 22:02
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 添加公告
     * @param notice
     * @return
     * @throws Exception
     */
    public int  addNotic(Notice notice)throws Exception{
        notice.setId(IDUtils.createID());
        notice.setCreateTime(new Date());
        notice.setIsShow("0");
        return noticeMapper.insertSelective(notice);
    }

    /**
     * 公告列表
     * @return
     * @throws Exception
     */
    public List<Notice>list()throws Exception{
        return noticeMapper.noticeList();
    }

    /**
     * 公告详情
     * @param id
     * @return
     * @throws Exception
     */
    public Notice select(String id)throws Exception{
        return noticeMapper.selectByPrimaryKey(id);
    }

    /**
     * 个人公告管理
     * @return
     * @throws Exception
     */
    public List<Notice>selectByCreateor(Notice notice)throws Exception{
        return noticeMapper.selectByCreateor(notice);
    }

    /**
     * 删除
     * @param list
     * @return
     * @throws Exception
     */
    public int delete(List<Notice> list)throws Exception{
        int deleteNum=0;
        for(Notice notice:list){
            noticeMapper.deleteByPrimaryKey(notice.getId());
            deleteNum++;
        }
        return deleteNum;
    }
}
