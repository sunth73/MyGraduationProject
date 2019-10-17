package com.wmk.porject.bean;

public class Score {
    private String recId;

    private String sStuId;

    private String sSubCode;

    private String sScore;

    private String sSemester;

    private String sExamId;

    public String getsExamId() {
        return sExamId;
    }

    public void setsExamId(String sExamId) {
        this.sExamId = sExamId;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId == null ? null : recId.trim();
    }

    public String getsStuId() {
        return sStuId;
    }

    public void setsStuId(String sStuId) {
        this.sStuId = sStuId == null ? null : sStuId.trim();
    }

    public String getsSubCode() {
        return sSubCode;
    }

    public void setsSubCode(String sSubCode) {
        this.sSubCode = sSubCode == null ? null : sSubCode.trim();
    }

    public String getsScore() {
        return sScore;
    }

    public void setsScore(String sScore) {
        this.sScore = sScore == null ? null : sScore.trim();
    }

    public String getsSemester() {
        return sSemester;
    }

    public void setsSemester(String sSemester) {
        this.sSemester = sSemester == null ? null : sSemester.trim();
    }
}