package com.federicogualdi.shipmenthub.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Coordinate implements Serializable {

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public static Coordinate Create(double latitude, double longitude){
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(latitude);
        coordinate.setLongitude(longitude);
        return  coordinate;
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

    @Override
    public String toString() {
        return "Coordinate{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
