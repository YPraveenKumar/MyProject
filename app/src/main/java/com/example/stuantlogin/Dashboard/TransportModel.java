package com.example.stuantlogin.Dashboard;

public class TransportModel  {
    private String sSub, sMsg, name, rollno, std, email;

    public TransportModel() {
    }

    public TransportModel(String sSub, String sMsg, String name, String rollno, String std, String email) {
        this.sSub = sSub;
        this.sMsg = sMsg;
        this.name = name;
        this.rollno = rollno;
        this.std = std;
        this.email = email;
    }

    public String getsSub() {
        return sSub;
    }

    public void setsSub(String sSub) {
        this.sSub = sSub;
    }

    public String getsMsg() {
        return sMsg;
    }

    public void setsMsg(String sMsg) {
        this.sMsg = sMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

