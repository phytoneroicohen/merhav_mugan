package com.example.merhav_mugan;

public class merhav_mugan {
    long id;
    double latitude , longitude ;
    boolean accessible;
    int quantity;

    public long getid() {
        return id;
    }

    public void setid(long id) {
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

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return  "accessible=" + accessible +
                ", quantity=" + quantity ;
    }

    public merhav_mugan(long id, double latitude, double longitude, boolean accessible, int quantity) {

        this.id=id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accessible = accessible;
        this.quantity = quantity;
    }

    public merhav_mugan() {
    }
}
