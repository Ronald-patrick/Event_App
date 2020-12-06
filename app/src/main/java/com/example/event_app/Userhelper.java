package com.example.event_app;

public class Userhelper {
    String name,roll,email,password,confirmpass;
    public Userhelper()
    {

    }
    public Userhelper(String name, String roll, String email, String password, String confirmpass) {
        this.name = name;
        this.roll = roll;
        this.email = email;
        this.password = password;
        this.confirmpass = confirmpass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }
}
