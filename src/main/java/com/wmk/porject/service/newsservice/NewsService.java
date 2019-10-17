package com.wmk.porject.service.newsservice;

import com.wmk.porject.bean.News;
import com.wmk.porject.bean.NewsResponse;
import com.wmk.porject.mapper.newsmapper.NewsMapper;
import com.wmk.porject.mapper.newsresponsemapper.NewsResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-05-06 16:28
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private NewsResponseMapper responseMapper;

    /**
     * 未读消息数量
     * @param receiveId
     * @return
     * @throws Exception
     */
    public int newsnum(Integer receiveId)throws Exception{
        return newsMapper.newsnum(receiveId);
    }

    /**
     * 消息列表
     * @param news
     * @return
     * @throws Exception
     */
    public List<News>list(News news)throws Exception{
        return newsMapper.newslist(news);
    }
    /**
     * 消息列表
     * @param news
     * @return
     * @throws Exception
     */
    public List<News>listtwo(News news)throws Exception{
        return newsMapper.newslisttwo(news);
    }

    /**
     * 接受者查看留言详情
     * @param id
     * @return
     * @throws Exception
     */
    public News select(String id)throws Exception{
        News news=new News();
        news.setId(id);
        news.setIsShow("0");
        newsMapper.updateByPrimaryKeySelective(news);
        return newsMapper.selectByPrimaryKey(id);
    }
    /**
     * 发送者查看留言详情
     * @param id
     * @return
     * @throws Exception
     */
    public News selecttwo(String id)throws Exception{
        return newsMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加留言
     * @param news
     * @return
     * @throws Exception
     */
    public int add(News news)throws Exception{
        return newsMapper.insertSelective(news);
    }
    /**
     * 消息列表
     * @param news1
     * @return
     * @throws Exception
     */
    public List<News>newsList(News news1)throws Exception{
        List<News>list=newsMapper.newslist(news1);
        List<News>list2=new ArrayList<>();
        for(News news:list){
          List<NewsResponse> list1= responseMapper.selectByNewsId(news.getId());
          news.setNewsResponseList(list1);
          list2.add(news);
        }
        return list2;
    }

    /**
     * 删除留言
     * @param list
     * @return
     * @throws Exception
     */
    public int delete(List<News> list)throws Exception{
        int deleteNum=0;
        for(News news:list){
            newsMapper.deleteByPrimaryKey(news.getId());
            deleteNum++;
        }
        return deleteNum;
    }
}
