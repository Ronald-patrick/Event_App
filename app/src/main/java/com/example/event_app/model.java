package com.example.event_app;

public class model {
    String title,date,org;

    model()
    {

    }
    public model(String title, String date, String org) {
        this.title = title;
        this.date = date;
        this.org =org;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = "Date: "+date;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org ="By "+ org;
    }
}