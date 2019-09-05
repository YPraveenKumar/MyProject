package com.example.stuantlogin.AdminPage;

public class BillingModel {
    private String sName, sRollno, sClassName, sTotalFee, sPaidFee, sBalanceFee, sDueDate;

    //Empty Constructor
    public BillingModel() {
    }

    //parameter Constructor
    public BillingModel(String sName, String sRollno, String sClassName, String sTotalFee, String sPaidFee, String sBalanceFee, String sDueDate) {
        this.sName = sName;
        this.sRollno = sRollno;
        this.sClassName = sClassName;
        this.sTotalFee = sTotalFee;
        this.sPaidFee = sPaidFee;
        this.sBalanceFee = sBalanceFee;
        this.sDueDate = sDueDate;
    }

    //Getter-Setter
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

    public String getsClassName() {
        return sClassName;
    }

    public void setsClassName(String sClassName) {
        this.sClassName = sClassName;
    }

    public String getsTotalFee() {
        return sTotalFee;
    }

    public void setsTotalFee(String sTotalFee) {
        this.sTotalFee = sTotalFee;
    }

    public String getsPaidFee() {
        return sPaidFee;
    }

    public void setsPaidFee(String sPaidFee) {
        this.sPaidFee = sPaidFee;
    }

    public String getsBalanceFee() {
        return sBalanceFee;
    }

    public void setsBalanceFee(String sBalanceFee) {
        this.sBalanceFee = sBalanceFee;
    }

    public String getsDueDate() {
        return sDueDate;
    }

    public void setsDueDate(String sDueDate) {
        this.sDueDate = sDueDate;
    }

}
