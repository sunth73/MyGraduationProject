package com.wmk.porject.bean;

public class StudentSubsidy {
    private String recId;

    private String tUserId;

    private String tSubsidyId;

    private String tStuCardNum;

    public String gettStuCardNum() {
        return tStuCardNum;
    }

    public void settStuCardNum(String tStuCardNum) {
        this.tStuCardNum = tStuCardNum;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId == null ? null : recId.trim();
    }

    public String gettUserId() {
        return tUserId;
    }

    public void settUserId(String tUserId) {
        this.tUserId = tUserId == null ? null : tUserId.trim();
    }

    public String gettSubsidyId() {
        return tSubsidyId;
    }

    public void settSubsidyId(String tSubsidyId) {
        this.tSubsidyId = tSubsidyId == null ? null : tSubsidyId.trim();
    }
}