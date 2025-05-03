package com.example.merhav_mugan;

public class User {
    String UN;
    String address;
    String password;
    String email;
    boolean needAccessibility;

    public boolean IsNeedAccessibility() {
        return needAccessibility;
    }

    public void setNeedAccessibility(boolean needAccessibility) {
        this.needAccessibility = needAccessibility;
    }

    public String getUN() {
        return UN;
    }

    public void setUN(String UN) {
        this.UN = UN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {

    }

    public String getAddress() {
        return address;
    }

    public User(String UN, String password, String email, String address, boolean needacesability){


        this.UN = UN;
        this.password = password;
        this.email = email;
        this.address=address;
        this.needAccessibility=needAccessibility;
    }

}
