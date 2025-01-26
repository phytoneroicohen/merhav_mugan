package com.example.merhav_mugan;

public class merhav_mugan {
    int id;
    double latitude , longitude ;
    int is_accessible;
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

    public int is_accessible() {
        return is_accessible;
    }

    public void setis_accessible(int is_accessible) {
        this.is_accessible = is_accessible;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return  "is_accessible=" + is_accessible +
                ", quantity=" + quantity ;
    }

    public merhav_mugan(int id, double latitude, double longitude, int is_accessible, int quantity) {

        this.id=id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.is_accessible = is_accessible;
        this.quantity = quantity;
    }
}
