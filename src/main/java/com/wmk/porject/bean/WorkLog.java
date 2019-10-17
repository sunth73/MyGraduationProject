package com.wmk.porject.bean;

import java.util.Date;

public class WorkLog {
    private String tId;

    private String logType;

    private String logDate;

    private String logValue;

    private String logUserId;

    private String logFlag;

    private String flag;

    private String maxDate;

    private String minDate;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getLogValue() {
        return logValue;
    }

    public void setLogValue(String logValue) {
        this.logValue = logValue;
    }

    public String getLogUserId() {
        return logUserId;
    }

    public void setLogUserId(String logUserId) {
        this.logUserId = logUserId;
    }

    public String getLogFlag() {
        return logFlag;
    }

    public void setLogFlag(String logFlag) {
        this.logFlag = logFlag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }
}