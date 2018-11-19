package com.example.eunarbdayangco.younghomesapp.Model;

import java.sql.Timestamp;

public class Student {

    private int studentID;
    private String schoolID;
    private String fullname,address,contactNumber,level;
    private Timestamp addedDate;
    private boolean active,form138,birthCertificate;
    private User user;
    private boolean graduated;

    public Student() {
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String grade) {
        this.level = grade;
    }




    public boolean isForm138() {
        return form138;
    }

    public void setForm138(boolean form138) {
        this.form138 = form138;
    }

    public boolean isBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(boolean birthCertificate) {
        this.birthCertificate = birthCertificate;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

}
