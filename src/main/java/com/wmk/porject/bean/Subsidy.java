package com.wmk.porject.bean;

public class Subsidy {
    private String tsuId;

    private String tsuStarDate;

    private String tsuEndDate;

    private String tsuSubsidy;

    private String tsuSubsidyCode;

    private String currentDate;

    private String tsuNum;

    private String tsuMoney;

    private String tsuState;

    public String getTsuState() {
        return tsuState;
    }

    public void setTsuState(String tsuState) {
        this.tsuState = tsuState;
    }

    public String getTsuNum() {
        return tsuNum;
    }

    public void setTsuNum(String tsuNum) {
        this.tsuNum = tsuNum;
    }

    public String getTsuMoney() {
        return tsuMoney;
    }

    public void setTsuMoney(String tsuMoney) {
        this.tsuMoney = tsuMoney;
    }

    public String getTsuId() {
        return tsuId;
    }

    public void setTsuId(String tsuId) {
        this.tsuId = tsuId == null ? null : tsuId.trim();
    }

    public String getTsuStarDate() {
        return tsuStarDate;
    }

    public void setTsuStarDate(String tsuStarDate) {
        this.tsuStarDate = tsuStarDate == null ? null : tsuStarDate.trim();
    }

    public String getTsuEndDate() {
        return tsuEndDate;
    }

    public void setTsuEndDate(String tsuEndDate) {
        this.tsuEndDate = tsuEndDate == null ? null : tsuEndDate.trim();
    }

    public String getTsuSubsidy() {
        return tsuSubsidy;
    }

    public void setTsuSubsidy(String tsuSubsidy) {
        this.tsuSubsidy = tsuSubsidy == null ? null : tsuSubsidy.trim();
    }

    public String getTsuSubsidyCode() {
        return tsuSubsidyCode;
    }

    public void setTsuSubsidyCode(String tsuSubsidyCode) {
        this.tsuSubsidyCode = tsuSubsidyCode == null ? null : tsuSubsidyCode.trim();
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}