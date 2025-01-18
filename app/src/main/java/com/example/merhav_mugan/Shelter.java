package com.example.merhav_mugan;

public class Shelter {
    private String Address;
    private double latitude;
    private double longitude;
    private boolean is_acccesable;
    int number_pepole_can_come;

    public Shelter(String address, double latitude, double longitude, boolean is_acccesable, int number_pepole_can_come) {
        Address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.is_acccesable = is_acccesable;
        this.number_pepole_can_come = number_pepole_can_come;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isIs_acccesable() {
        return is_acccesable;
    }

    public void setIs_acccesable(boolean is_acccesable) {
        this.is_acccesable = is_acccesable;
    }

    public int getNumber_pepole_can_come() {
        return number_pepole_can_come;
    }

    public void setNumber_pepole_can_come(int number_pepole_can_come) {
        this.number_pepole_can_come = number_pepole_can_come;
    }
}
