package com.wmk.porject.bean;

public class SubsidyApplication {
    private String id;

    private String tStuCardNum;

    private String tApplicationDate;

    private String tDescribe;

    private String tGrade;

    private String tClassNum;

    private String tTsuId;

    private String stuName;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String gettStuCardNum() {
        return tStuCardNum;
    }

    public void settStuCardNum(String tStuCardNum) {
        this.tStuCardNum = tStuCardNum == null ? null : tStuCardNum.trim();
    }

    public String gettApplicationDate() {
        return tApplicationDate;
    }

    public void settApplicationDate(String tApplicationDate) {
        this.tApplicationDate = tApplicationDate == null ? null : tApplicationDate.trim();
    }

    public String gettDescribe() {
        return tDescribe;
    }

    public void settDescribe(String tDescribe) {
        this.tDescribe = tDescribe == null ? null : tDescribe.trim();
    }

    public String gettGrade() {
        return tGrade;
    }

    public void settGrade(String tGrade) {
        this.tGrade = tGrade == null ? null : tGrade.trim();
    }

    public String gettClassNum() {
        return tClassNum;
    }

    public void settClassNum(String tClassNum) {
        this.tClassNum = tClassNum == null ? null : tClassNum.trim();
    }

    public String gettTsuId() {
        return tTsuId;
    }

    public void settTsuId(String tTsuId) {
        this.tTsuId = tTsuId == null ? null : tTsuId.trim();
    }
}