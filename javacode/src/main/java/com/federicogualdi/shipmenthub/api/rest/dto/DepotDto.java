package com.federicogualdi.shipmenthub.api.rest.dto;

public class DepotDto {

    public int id;
    public double longitude;
    public double latitude;

    @Override
    public String toString() {
        return "DepotDto{" + "id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }
}
