package com.example.stuantlogin.Dashboard;

public class ProgRegModel {
    private String pID, pCode, pType, pName, pDet, pOrg, pDate, cID, cName, cFee, cTime, cDate, sID, sCode, sName, aName,
            aNo, aTeamName;

    public ProgRegModel() {
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public ProgRegModel(String pID, String pCode, String pType, String pName, String pDet, String pOrg, String pDate, String cID, String cName, String cFee, String cTime, String cDate, String sID, String sCode, String sName, String aName, String aNo, String aTeamName) {
        this.pID = pID;
        this.pCode = pCode;
        this.pType = pType;
        this.pName = pName;
        this.pDet = pDet;
        this.pOrg = pOrg;
        this.pDate = pDate;
        this.cID = cID;
        this.cName = cName;
        this.cFee = cFee;
        this.cTime = cTime;
        this.cDate = cDate;
        this.sID = sID;
        this.sCode = sCode;
        this.sName = sName;
        this.aName = aName;
        this.aNo = aNo;
        this.aTeamName = aTeamName;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDet() {
        return pDet;
    }

    public void setpDet(String pDet) {
        this.pDet = pDet;
    }

    public String getpOrg() {
        return pOrg;
    }

    public void setpOrg(String pOrg) {
        this.pOrg = pOrg;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcFee() {
        return cFee;
    }

    public void setcFee(String cFee) {
        this.cFee = cFee;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaNo() {
        return aNo;
    }

    public void setaNo(String aNo) {
        this.aNo = aNo;
    }

    public String getaTeamName() {
        return aTeamName;
    }

    public void setaTeamName(String aTeamName) {
        this.aTeamName = aTeamName;
    }
}
