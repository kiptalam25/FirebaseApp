package com.sqlite.firebaseapp;

public class User {

    private  String fname;
    private  String lname;

    public User(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public User() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
