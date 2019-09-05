package com.example.stuantlogin.AdminPage;

public class TimeTableModule {

    private String sStd, sDay, sHr1, sHr2, sHr3, sHr4, sHr5, sHr6, sHr7, sHr8;

    public TimeTableModule() {
    }

    public TimeTableModule(String sHr1, String sHr2, String sHr3, String sHr4, String sHr5, String sHr6, String sHr7, String sHr8) {
        this.sHr1 = sHr1;
        this.sHr2 = sHr2;
        this.sHr3 = sHr3;
        this.sHr4 = sHr4;
        this.sHr5 = sHr5;
        this.sHr6 = sHr6;
        this.sHr7 = sHr7;
        this.sHr8 = sHr8;
    }

    public TimeTableModule(String sStd, String sDay, String ssHr1, String sHr2, String sHr3, String sHr4, String sHr5, String sHr6, String sHr7, String sHr8) {
        this.sStd = sStd;
        this.sDay = sDay;
        this.sHr1 = sHr1;
        this.sHr2 = sHr2;
        this.sHr3 = sHr3;
        this.sHr4 = sHr4;
        this.sHr5 = sHr5;
        this.sHr6 = sHr6;
        this.sHr7 = sHr7;
        this.sHr8 = sHr8;
    }

    public String getsStd() {
        return sStd;
    }

    public void setsStd(String sStd) {
        this.sStd = sStd;
    }

    public String getsDay() {
        return sDay;
    }

    public void setsDay(String sDay) {
        this.sDay = sDay;
    }

    public String getsHr1() {
        return sHr1;
    }

    public void setsHr1(String sHr1) {
        this.sHr1 = sHr1;
    }

    public String getsHr2() {
        return sHr2;
    }

    public void setsHr2(String sHr2) {
        this.sHr2 = sHr2;
    }

    public String getsHr3() {
        return sHr3;
    }

    public void setsHr3(String sHr3) {
        this.sHr3 = sHr3;
    }

    public String getsHr4() {
        return sHr4;
    }

    public void setsHr4(String sHr4) {
        this.sHr4 = sHr4;
    }

    public String getsHr5() {
        return sHr5;
    }

    public void setsHr5(String sHr5) {
        this.sHr5 = sHr5;
    }

    public String getsHr6() {
        return sHr6;
    }

    public void setsHr6(String sHr6) {
        this.sHr6 = sHr6;
    }

    public String getsHr7() {
        return sHr7;
    }

    public void setsHr7(String sHr7) {
        this.sHr7 = sHr7;
    }

    public String getsHr8() {
        return sHr8;
    }

    public void setsHr8(String sHr8) {
        this.sHr8 = sHr8;
    }
}
