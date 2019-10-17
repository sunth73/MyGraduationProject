package com.wmk.porject.request;

/**
 * @author Sunth
 * @DateTime 2019-04-17 15:35
 * 描述
 */
public class DormScoreRequest {
    private String dormId;

    private String dormNum;

    private String dormClassNum;

    private String dormUserId;

    private String tdsId;

    private String tdsDormId;

    private String maxDate;

    private String tdsDate;

    private String  minDate;

    private String tdsGrade;

    private String tdsEvaluate;

    private String currentUser;

    private String ranking;

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getDormNum() {
        return dormNum;
    }

    public void setDormNum(String dormNum) {
        this.dormNum = dormNum;
    }

    public String getDormClassNum() {
        return dormClassNum;
    }

    public void setDormClassNum(String dormClassNum) {
        this.dormClassNum = dormClassNum;
    }

    public String getDormUserId() {
        return dormUserId;
    }

    public void setDormUserId(String dormUserId) {
        this.dormUserId = dormUserId;
    }

    public String getTdsId() {
        return tdsId;
    }

    public void setTdsId(String tdsId) {
        this.tdsId = tdsId;
    }

    public String getTdsDormId() {
        return tdsDormId;
    }

    public void setTdsDormId(String tdsDormId) {
        this.tdsDormId = tdsDormId;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public String getTdsDate() {
        return tdsDate;
    }

    public void setTdsDate(String tdsDate) {
        this.tdsDate = tdsDate;
    }

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    public String getTdsGrade() {
        return tdsGrade;
    }

    public void setTdsGrade(String tdsGrade) {
        this.tdsGrade = tdsGrade;
    }

    public String getTdsEvaluate() {
        return tdsEvaluate;
    }

    public void setTdsEvaluate(String tdsEvaluate) {
        this.tdsEvaluate = tdsEvaluate;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
