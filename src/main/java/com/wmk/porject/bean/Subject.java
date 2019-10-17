package com.wmk.porject.bean;

public class Subject {
    private String id;

    private String subCode;

    private String subName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode == null ? null : subCode.trim();
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }
}