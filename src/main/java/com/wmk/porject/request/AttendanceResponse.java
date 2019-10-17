package com.wmk.porject.request;

import java.util.Date;

/**
 * @author Sunth
 * @DateTime 2019-05-23 12:36
 * 描述
 */
public class AttendanceResponse {
    private String tId;

    private Date tDate;

    private String tWeek;

    private String tWorkState;

    private String tState;

    private String tCause;

    private String tLeave;

    private String tStuId;

    private String tSubId;

    private String tNum;

    private String stuCardNum;

    private String stuId;

    private String stuName;

    private String stuClass;

    private String subName;

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    public String gettWeek() {
        return tWeek;
    }

    public void settWeek(String tWeek) {
        this.tWeek = tWeek;
    }

    public String gettWorkState() {
        return tWorkState;
    }

    public void settWorkState(String tWorkState) {
        this.tWorkState = tWorkState;
    }

    public String gettState() {
        return tState;
    }

    public void settState(String tState) {
        this.tState = tState;
    }

    public String gettCause() {
        return tCause;
    }

    public void settCause(String tCause) {
        this.tCause = tCause;
    }

    public String gettLeave() {
        return tLeave;
    }

    public void settLeave(String tLeave) {
        this.tLeave = tLeave;
    }

    public String gettStuId() {
        return tStuId;
    }

    public void settStuId(String tStuId) {
        this.tStuId = tStuId;
    }

    public String gettSubId() {
        return tSubId;
    }

    public void settSubId(String tSubId) {
        this.tSubId = tSubId;
    }

    public String gettNum() {
        return tNum;
    }

    public void settNum(String tNum) {
        this.tNum = tNum;
    }

    public String getStuCardNum() {
        return stuCardNum;
    }

    public void setStuCardNum(String stuCardNum) {
        this.stuCardNum = stuCardNum;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }
}
