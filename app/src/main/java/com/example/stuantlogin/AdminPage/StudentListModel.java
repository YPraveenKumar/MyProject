package com.example.stuantlogin.AdminPage;

public class StudentListModel {
    private String sName, sRollno, sGender, sDate, sEmail, sAddress, sPhone, sClassName;

    public StudentListModel() {
    }

    public StudentListModel(String sName, String sRollno, String sGender, String sDate, String sEmail, String sAddress, String sPhone, String sClassName) {
        this.sName = sName;
        this.sRollno = sRollno;
        this.sGender = sGender;
        this.sDate = sDate;
        this.sEmail = sEmail;
        this.sAddress = sAddress;
        this.sPhone = sPhone;
        this.sClassName = sClassName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsRollno() {
        return sRollno;
    }

    public void setsRollno(String sRollno) {
        this.sRollno = sRollno;
    }

    public String getsGender() {
        return sGender;
    }

    public void setsGender(String sGender) {
        this.sGender = sGender;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsClassName() {
        return sClassName;
    }

    public void setsClassName(String sClassName) {
        this.sClassName = sClassName;
    }
}
