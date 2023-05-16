package com.federicogualdi.shipmenthub.api.rest.dto;

public class CreatePackageDto {
    public double latitude;
    public double longitude;

    @Override
    public String toString() {
        return "CreatePackageDto{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }
}
