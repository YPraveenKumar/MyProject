package com.example.stuantlogin.AdminPage;

public class AssessmentModel {

    private String sName;
    private String sRollNo;
    private String sSub1;
    private String sSub2;
    private String sSub3;
    private String sTotal;
    private String sPercentage;
    private String sRecommendation;
    private String sStuActivity;
    private String sAttendance;
    private String sGrade;
    private String sTerm;

    public AssessmentModel() {
    }

    public AssessmentModel(String sName, String sRollNo, String sSub1, String sSub2, String sSub3, String sTotal, String sPercentage, String sRecommendation, String sStuActivity, String sAttendance,String sGrade, String sTerm) {
        this.sName = sName;
        this.sRollNo = sRollNo;
        this.sSub1 = sSub1;
        this.sSub2 = sSub2;
        this.sSub3 = sSub3;
        this.sTotal = sTotal;
        this.sPercentage = sPercentage;
        this.sRecommendation = sRecommendation;
        this.sStuActivity = sStuActivity;
        this.sAttendance = sAttendance;
        this.sGrade = sGrade;
        this.sTerm = sTerm;
    }

    public String getsGrade() {
        return sGrade;
    }

    public void setsGrade(String sGrade) {
        this.sGrade = sGrade;
    }

    public String getsTerm() {
        return sTerm;
    }

    public void setsTerm(String sTerm) {
        this.sTerm = sTerm;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsRollNo() {
        return sRollNo;
    }

    public void setsRollNo(String sRollNo) {
        this.sRollNo = sRollNo;
    }

    public String getsSub1() {
        return sSub1;
    }

    public void setsSub1(String sSub1) {
        this.sSub1 = sSub1;
    }

    public String getsSub2() {
        return sSub2;
    }

    public void setsSub2(String sSub2) {
        this.sSub2 = sSub2;
    }

    public String getsSub3() {
        return sSub3;
    }

    public void setsSub3(String sSub3) {
        this.sSub3 = sSub3;
    }

    public String getsTotal() {
        return sTotal;
    }

    public void setsTotal(String sTotal) {
        this.sTotal = sTotal;
    }

    public String getsPercentage() {
        return sPercentage;
    }

    public void setsPercentage(String sPercentage) {
        this.sPercentage = sPercentage;
    }

    public String getsRecommendation() {
        return sRecommendation;
    }

    public void setsRecommendation(String sRecommendation) {
        this.sRecommendation = sRecommendation;
    }

    public String getsStuActivity() {
        return sStuActivity;
    }

    public void setsStuActivity(String sStuActivity) {
        this.sStuActivity = sStuActivity;
    }

    public String getsAttendance() {
        return sAttendance;
    }

    public void setsAttendance(String sAttendance) {
        this.sAttendance = sAttendance;
    }
}
