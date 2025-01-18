package com.example.merhav_mugan;

public class merhav_mugan {
    String adres;
    double letitude , longitude ;
boolean is_acictive;
int quantity;

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public double getLetitude() {
        return letitude;
    }

    public void setLetitude(double letitude) {
        this.letitude = letitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isIs_acictive() {
        return is_acictive;
    }

    public void setIs_acictive(boolean is_acictive) {
        this.is_acictive = is_acictive;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public merhav_mugan(String adres, double letitude, double longitude, boolean is_acictive, int quantity) {
        this.adres = adres;
        this.letitude = letitude;
        this.longitude = longitude;
        this.is_acictive = is_acictive;
        this.quantity = quantity;
    }
}
