package com.wmk.porject.request;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @author Sunth
 * @DateTime 2019-04-18 16:04
 * 描述
 */
@ExcelTarget("ScoreRequest")
public class ScoreRequest {
    @Excel(name = "班级",orderNum = "0",isWrap = false)
    private String stuClass;
    @Excel(name = "学号",orderNum = "1",isWrap = false)
    private String stuCardNum;
    @Excel(name = "姓名",orderNum = "2",isWrap = false)
    private String stuName;
    @Excel(name = "语文",orderNum = "3",isWrap = false)
    private String chinese;
    @Excel(name = "数学",orderNum = "4",isWrap = false)
    private String math;
    @Excel(name = "英语",orderNum = "5",isWrap = false)
    private String english;
    @Excel(name = "物理",orderNum = "6",isWrap = false)
    private String physics;//物理
    @Excel(name = "化学",orderNum = "7",isWrap = false)
    private String chemistry;//化学
    @Excel(name = "生物",orderNum = "8",isWrap = false)
    private String biology;//生物
    @Excel(name = "地理",orderNum = "9",isWrap = false)
    private String geography;//地理
    @Excel(name = "历史",orderNum = "10",isWrap = false)
    private String history;//历史
    @Excel(name = "政治",orderNum = "11",isWrap = false)
    private String politics;//政治
    @Excel(name = "总分",orderNum = "12",isWrap = false)
    private String totalScore;//总分
    private String average;//平均分
    @Excel(name = "排名",orderNum = "13",isWrap = false)
    private String ranking;

    private String sSemester;

    private String parentPhone;


    private String sExamId;

    private String eName;

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getsExamId() {
        return sExamId;
    }

    public void setsExamId(String sExamId) {
        this.sExamId = sExamId;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getsSemester() {
        return sSemester;
    }

    public void setsSemester(String sSemester) {
        this.sSemester = sSemester;
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

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPhysics() {
        return physics;
    }

    public void setPhysics(String physics) {
        this.physics = physics;
    }

    public String getChemistry() {
        return chemistry;
    }

    public void setChemistry(String chemistry) {
        this.chemistry = chemistry;
    }

    public String getBiology() {
        return biology;
    }

    public void setBiology(String biology) {
        this.biology = biology;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
