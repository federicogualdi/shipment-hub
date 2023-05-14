package com.federicogualdi.shipmenthub.api.rest.dto;

public class PackageDto {

    public Integer id;
    public OrderDto orderDto;
    public double latitude;
    public double longitude;

    @Override
    public String toString() {
        return "PackageDto{" + "id=" + id + ", orderDto=" + orderDto + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }
}
