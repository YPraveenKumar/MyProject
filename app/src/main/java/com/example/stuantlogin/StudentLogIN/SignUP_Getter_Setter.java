package com.example.stuantlogin.StudentLogIN;

public class SignUP_Getter_Setter {
    private String name, rollNo, dob, standard, email, father, mother, blood, address, extra, gender;

    public SignUP_Getter_Setter(String name, String rollNo, String dob, String standard, String email, String father, String mother, String blood, String address, String extra, String gender) {
        this.name = name;
        this.rollNo = rollNo;
        this.dob = dob;
        this.standard = standard;
        this.email = email;
        this.father = father;
        this.mother = mother;
        this.blood = blood;
        this.address = address;
        this.extra = extra;
        this.gender = gender;
    }

    public SignUP_Getter_Setter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
