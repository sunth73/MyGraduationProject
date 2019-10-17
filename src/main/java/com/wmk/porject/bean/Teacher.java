package com.wmk.porject.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("Teacher")
public class Teacher {
    private String teaId;
    @Excel(name = "姓名",orderNum ="0" ,isWrap = false)
    private String teaName;
    @Excel(name = "登录名",orderNum = "1",isWrap = false)
    private String teaLoginName;
    @Excel(name = "班级",orderNum ="2" ,isWrap = false)
    private String teaCalss;
    @Excel(name = "班级人数",orderNum = "3",isWrap = false)
    private String teaClassNum;
    @Excel(name = "工号",orderNum = "4",isWrap = false)
    private String teaCardNum;
    @Excel(name = "姓别",replace = { "男_1", "女_2" },orderNum = "5",isWrap = false)
    private String teaSex;
    @Excel(name = "专业",orderNum = "6",isWrap = false)
    private String teaSubject;
    @Excel(name = "政治面貌",orderNum = "7",isWrap = false)
    private String teaPolicital;
    @Excel(name = "手机号",orderNum = "8",isWrap = false)
    private String teaPhone;
    @Excel(name = "QQ号",orderNum = "9",isWrap = false)
    private String teaQq;
    @Excel(name = "微信",orderNum = "10",isWrap = false)
    private String teaWchart;
    @Excel(name = "毕业院校",orderNum = "11",isWrap = false)
    private String teaUniversity;
    @Excel(name = "学历",orderNum = "12",isWrap = false)
    private String teaEducation;
    @Excel(name = "住址",orderNum = "13",isWrap = false)
    private String teaAddress;
    private String teaPhoteUrl;
    @Excel(name = "邮箱",orderNum = "14",isWrap = false)
    private String teaMail;
    @Excel(name = "身份证号",orderNum = "15",isWrap = false)
    private String teaPeopleCard;

    public String getTeaPeopleCard() {
        return teaPeopleCard;
    }

    public void setTeaPeopleCard(String teaPeopleCard) {
        this.teaPeopleCard = teaPeopleCard;
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId == null ? null : teaId.trim();
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public String getTeaLoginName() {
        return teaLoginName;
    }

    public void setTeaLoginName(String teaLoginName) {
        this.teaLoginName = teaLoginName == null ? null : teaLoginName.trim();
    }

    public String getTeaCalss() {
        return teaCalss;
    }

    public void setTeaCalss(String teaCalss) {
        this.teaCalss = teaCalss == null ? null : teaCalss.trim();
    }

    public String getTeaClassNum() {
        return teaClassNum;
    }

    public void setTeaClassNum(String teaClassNum) {
        this.teaClassNum = teaClassNum == null ? null : teaClassNum.trim();
    }

    public String getTeaCardNum() {
        return teaCardNum;
    }

    public void setTeaCardNum(String teaCardNum) {
        this.teaCardNum = teaCardNum == null ? null : teaCardNum.trim();
    }

    public String getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(String teaSex) {
        this.teaSex = teaSex == null ? null : teaSex.trim();
    }

    public String getTeaSubject() {
        return teaSubject;
    }

    public void setTeaSubject(String teaSubject) {
        this.teaSubject = teaSubject == null ? null : teaSubject.trim();
    }

    public String getTeaPolicital() {
        return teaPolicital;
    }

    public void setTeaPolicital(String teaPolicital) {
        this.teaPolicital = teaPolicital == null ? null : teaPolicital.trim();
    }

    public String getTeaPhone() {
        return teaPhone;
    }

    public void setTeaPhone(String teaPhone) {
        this.teaPhone = teaPhone == null ? null : teaPhone.trim();
    }

    public String getTeaQq() {
        return teaQq;
    }

    public void setTeaQq(String teaQq) {
        this.teaQq = teaQq == null ? null : teaQq.trim();
    }

    public String getTeaWchart() {
        return teaWchart;
    }

    public void setTeaWchart(String teaWchart) {
        this.teaWchart = teaWchart == null ? null : teaWchart.trim();
    }

    public String getTeaUniversity() {
        return teaUniversity;
    }

    public void setTeaUniversity(String teaUniversity) {
        this.teaUniversity = teaUniversity == null ? null : teaUniversity.trim();
    }

    public String getTeaEducation() {
        return teaEducation;
    }

    public void setTeaEducation(String teaEducation) {
        this.teaEducation = teaEducation == null ? null : teaEducation.trim();
    }

    public String getTeaAddress() {
        return teaAddress;
    }

    public void setTeaAddress(String teaAddress) {
        this.teaAddress = teaAddress == null ? null : teaAddress.trim();
    }

    public String getTeaPhoteUrl() {
        return teaPhoteUrl;
    }

    public void setTeaPhoteUrl(String teaPhoteUrl) {
        this.teaPhoteUrl = teaPhoteUrl == null ? null : teaPhoteUrl.trim();
    }

    public String getTeaMail() {
        return teaMail;
    }

    public void setTeaMail(String teaMail) {
        this.teaMail = teaMail == null ? null : teaMail.trim();
    }
}