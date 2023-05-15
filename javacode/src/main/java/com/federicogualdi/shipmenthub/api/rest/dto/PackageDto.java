package com.federicogualdi.shipmenthub.api.rest.dto;

import com.federicogualdi.shipmenthub.entities.enums.PackageStatus;

public class PackageDto {

    public int id;
    public double latitude;
    public double longitude;
    public PackageStatus status;

    @Override
    public String toString() {
        return "PackageDto{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", status=" + status + '}';
    }
}
