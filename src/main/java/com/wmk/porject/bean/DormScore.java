package com.wmk.porject.bean;

public class DormScore {
    private String tdsId;

    private String tdsDormId;

    private String tdsDate;

    private String tdsGrade;

    private String tdsEvaluate;

    public String getTdsId() {
        return tdsId;
    }

    public void setTdsId(String tdsId) {
        this.tdsId = tdsId == null ? null : tdsId.trim();
    }

    public String getTdsDormId() {
        return tdsDormId;
    }

    public void setTdsDormId(String tdsDormId) {
        this.tdsDormId = tdsDormId == null ? null : tdsDormId.trim();
    }

    public String getTdsDate() {
        return tdsDate;
    }

    public void setTdsDate(String tdsDate) {
        this.tdsDate = tdsDate == null ? null : tdsDate.trim();
    }

    public String getTdsGrade() {
        return tdsGrade;
    }

    public void setTdsGrade(String tdsGrade) {
        this.tdsGrade = tdsGrade == null ? null : tdsGrade.trim();
    }

    public String getTdsEvaluate() {
        return tdsEvaluate;
    }

    public void setTdsEvaluate(String tdsEvaluate) {
        this.tdsEvaluate = tdsEvaluate == null ? null : tdsEvaluate.trim();
    }
}