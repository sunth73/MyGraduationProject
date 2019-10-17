package com.wmk.porject.bean;

public class Dorm {
    private String dormId;

    private String dormNum;

    private String dormClassNum;

    private String dormUserId;

    private String dormUserName;

    public String getDormUserName() {
        return dormUserName;
    }

    public void setDormUserName(String dormUserName) {
        this.dormUserName = dormUserName;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId == null ? null : dormId.trim();
    }

    public String getDormNum() {
        return dormNum;
    }

    public void setDormNum(String dormNum) {
        this.dormNum = dormNum == null ? null : dormNum.trim();
    }

    public String getDormClassNum() {
        return dormClassNum;
    }

    public void setDormClassNum(String dormClassNum) {
        this.dormClassNum = dormClassNum == null ? null : dormClassNum.trim();
    }

    public String getDormUserId() {
        return dormUserId;
    }

    public void setDormUserId(String dormUserId) {
        this.dormUserId = dormUserId == null ? null : dormUserId.trim();
    }
}