package com.example.merhav_mugan;

public class User {
    private int id;
    private String UN;
    private String addres;
    private String password;
    private String email;

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

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {

    }

    public User(int id, String UN, String password, String email,String addres
    ) {
        this.id = id;
        this.UN = UN;
        this.password = password;
        this.email = email;
        this.addres=addres;
    }
}
