package com.federicogualdi.shipmenthub.api.rest.dto;

public class DepotDto {

    public int id;
    public String name;
    public double longitude;
    public double latitude;

    @Override
    public String toString() {
        return "DepotDto{" + "id=" + id + ", name='" + name + '\'' + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }
}
