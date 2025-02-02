package com.example.merhav_mugan;

public class User {
    private int id;
    private String UN;
    private String address;
    private String password;
    private String email;
    private int needAccessibility;

    public int getNeedAccessibility() {
        return needAccessibility;
    }

    public void setNeedAccessibility(int needAccessibility) {
        this.needAccessibility = needAccessibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User(int id, String UN, String password, String email, String address, int needacesability){

        }
    {
        this.id = id;
        this.UN = UN;
        this.password = password;
        this.email = email;
        this.address=address;
        this.needAccessibility=needAccessibility;
    }
}
