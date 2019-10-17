package com.wmk.porject.request;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author Sunth
 * @DateTime 2019-05-12 19:52
 * 描述
 */
public class ScoreReq {
    @Excel(name = "学号",orderNum = "1",isWrap = false)
    private String stuCardNum;
    @Excel(name = "姓名",orderNum = "2",isWrap = false)
    private String stuName;
    private String stuUname;
    @Excel(name = "班级",orderNum = "0",isWrap = false)
    private String stuClass;
    private String subCode;
    @Excel(name = "科目",orderNum = "3",isWrap = false)
    private String subName;
    @Excel(name = "分数",orderNum = "4",isWrap = false)
    private String sScore;
    private String sStuId;
    private String sSubCode;
    private String sExamId;
    private String eId;
    private String recId;
    @Excel(name = "名次",orderNum = "5",isWrap = false)
    private String rank;

    private String eName;

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStuCardNum() {
        return stuCardNum;
    }

    public void setStuCardNum(String stuCardNum) {
        this.stuCardNum = stuCardNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuUname() {
        return stuUname;
    }

    public void setStuUname(String stuUname) {
        this.stuUname = stuUname;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getsScore() {
        return sScore;
    }

    public void setsScore(String sScore) {
        this.sScore = sScore;
    }

    public String getsStuId() {
        return sStuId;
    }

    public void setsStuId(String sStuId) {
        this.sStuId = sStuId;
    }

    public String getsSubCode() {
        return sSubCode;
    }

    public void setsSubCode(String sSubCode) {
        this.sSubCode = sSubCode;
    }

    public String getsExamId() {
        return sExamId;
    }

    public void setsExamId(String sExamId) {
        this.sExamId = sExamId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }
}

