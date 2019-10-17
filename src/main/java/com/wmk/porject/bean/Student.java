package com.wmk.porject.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.Date;
@ExcelTarget("Student")
public class Student {
    private String stuId;
    @Excel(name = "学号",orderNum = "2",isWrap = false)
    private String stuCardNum;
    @Excel(name = "姓名",orderNum = "0",isWrap = false)
    private String stuName;
    @Excel(name = "姓别",replace = { "男_1", "女_2" },orderNum = "3",isWrap = false)
    private String stuSex;
    @Excel(name = "民族",orderNum = "4",isWrap = false)
    private String stuNation;
    @Excel(name = "生日",orderNum = "5",exportFormat = "yyyy-MM-dd",isWrap = false)
    private Date stuBirthday;
    @Excel(name = "籍贯",orderNum = "6",isWrap = false)
    private String stuPlace;
    @Excel(name = "入学时间",orderNum = "7",exportFormat = "yyyy-MM-dd",isWrap = false)
    private Date stuGoyear;
    @Excel(name = "住址",orderNum = "8",isWrap = false)
    private String stuAddress;
    @Excel(name = "班级",orderNum = "9",isWrap = false)
    private String stuClass;
    @Excel(name = "政治面貌",orderNum = "10",isWrap = false)
    private String stuPolicital;
    @Excel(name = "职位",orderNum = "11",isWrap = false)
    private String stuJob;
    @Excel(name = "宿舍号",orderNum = "12",isWrap = false)
    private String stuDorm;
    @Excel(name = "手机号",orderNum = "13",isWrap = false)
    private String stuPhone;
    @Excel(name = "QQ号",orderNum = "14",isWrap = false)
    private String stuQq;
    @Excel(name = "邮箱",orderNum = "15",isWrap = false)
    private String stuEmail;
    @Excel(name = "微信",orderNum = "16",isWrap = false)
    private String stuWchart;
    @Excel(name = "家长姓名",orderNum = "17",isWrap = false)
    private String stuParent;
    @Excel(name = "家长手机号",orderNum = "18",isWrap = false)
    private String stuParentPhone;
    @Excel(name = "描述",orderNum = "19",isWrap = false)
    private String stuOther;

    private String stuPhotoUrl;
    @Excel(name = "身份证号",orderNum = "21",isWrap = false)
    private String stuPeopleCard;//stu_people_card
    @Excel(name = "登录名",orderNum = "1",isWrap = false)
    private String stuUname;

    public String getStuPeopleCard() {
        return stuPeopleCard;
    }

    public void setStuPeopleCard(String stuPeopleCard) {
        this.stuPeopleCard = stuPeopleCard;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
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

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuNation() {
        return stuNation;
    }

    public void setStuNation(String stuNation) {
        this.stuNation = stuNation;
    }

    public Date getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(Date stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getStuPlace() {
        return stuPlace;
    }

    public void setStuPlace(String stuPlace) {
        this.stuPlace = stuPlace;
    }

    public Date getStuGoyear() {
        return stuGoyear;
    }

    public void setStuGoyear(Date stuGoyear) {
        this.stuGoyear = stuGoyear;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuPolicital() {
        return stuPolicital;
    }

    public void setStuPolicital(String stuPolicital) {
        this.stuPolicital = stuPolicital;
    }

    public String getStuJob() {
        return stuJob;
    }

    public void setStuJob(String stuJob) {
        this.stuJob = stuJob;
    }

    public String getStuDorm() {
        return stuDorm;
    }

    public void setStuDorm(String stuDorm) {
        this.stuDorm = stuDorm;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuQq() {
        return stuQq;
    }

    public void setStuQq(String stuQq) {
        this.stuQq = stuQq;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuWchart() {
        return stuWchart;
    }

    public void setStuWchart(String stuWchart) {
        this.stuWchart = stuWchart;
    }

    public String getStuParent() {
        return stuParent;
    }

    public void setStuParent(String stuParent) {
        this.stuParent = stuParent;
    }

    public String getStuParentPhone() {
        return stuParentPhone;
    }

    public void setStuParentPhone(String stuParentPhone) {
        this.stuParentPhone = stuParentPhone;
    }

    public String getStuOther() {
        return stuOther;
    }

    public void setStuOther(String stuOther) {
        this.stuOther = stuOther;
    }

    public String getStuPhotoUrl() {
        return stuPhotoUrl;
    }

    public void setStuPhotoUrl(String stuPhotoUrl) {
        this.stuPhotoUrl = stuPhotoUrl;
    }

    public String getStuUname() {
        return stuUname;
    }

    public void setStuUname(String stuUname) {
        this.stuUname = stuUname;
    }
}