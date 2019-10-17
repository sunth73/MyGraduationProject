package com.wmk.porject.bean;

public class SubsidyResult {
    private String id;

    private String tsTsuId;

    private String tsStuId;

    private String tsClass;

    private String tsStuName;

    private String tsMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTsTsuId() {
        return tsTsuId;
    }

    public void setTsTsuId(String tsTsuId) {
        this.tsTsuId = tsTsuId == null ? null : tsTsuId.trim();
    }

    public String getTsStuId() {
        return tsStuId;
    }

    public void setTsStuId(String tsStuId) {
        this.tsStuId = tsStuId == null ? null : tsStuId.trim();
    }

    public String getTsClass() {
        return tsClass;
    }

    public void setTsClass(String tsClass) {
        this.tsClass = tsClass == null ? null : tsClass.trim();
    }

    public String getTsStuName() {
        return tsStuName;
    }

    public void setTsStuName(String tsStuName) {
        this.tsStuName = tsStuName == null ? null : tsStuName.trim();
    }

    public String getTsMoney() {
        return tsMoney;
    }

    public void setTsMoney(String tsMoney) {
        this.tsMoney = tsMoney == null ? null : tsMoney.trim();
    }
}