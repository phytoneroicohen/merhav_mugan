package com.example.merhav_mugan;

public class merhav_mugan {
    int id;
    double latitude , longitude ;
    boolean is_accessible;
    int quantity;

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public double getlatitude() {
        return latitude;
    }

    public void setlatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean is_accessible() {
        return is_accessible;
    }

    public void setis_accessible(boolean is_accessible) {
        this.is_accessible = is_accessible;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public merhav_mugan(double latitude, double longitude, boolean is_accessible, int quantity) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.is_accessible = is_accessible;
        this.quantity = quantity;
    }
}
