package com.wmk.porject.bean;

import java.util.Date;
import java.util.List;

public class News {
    private String id;

    private String news;

    private Date tDate;

    private Integer sendId;

    private Integer receiveId;

    private String isShow;

    private String userName;

    private String receiveName;

    private List<NewsResponse>newsResponseList;

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public List<NewsResponse> getNewsResponseList() {
        return newsResponseList;
    }

    public void setNewsResponseList(List<NewsResponse> newsResponseList) {
        this.newsResponseList = newsResponseList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news == null ? null : news.trim();
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }
}