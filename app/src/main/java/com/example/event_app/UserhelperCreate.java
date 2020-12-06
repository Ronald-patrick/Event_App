package com.example.event_app;

public class UserhelperCreate {
    String title,org,date,number,deadline,des;
public UserhelperCreate(){}
    public UserhelperCreate(String title, String org, String date, String number, String deadline, String des) {
        this.title = title;
        this.org = org;
        this.date = date;
        this.number = number;
        this.deadline = deadline;
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
