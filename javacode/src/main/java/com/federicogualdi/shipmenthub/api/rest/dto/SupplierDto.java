package com.federicogualdi.shipmenthub.api.rest.dto;

public class SupplierDto {

    public int id;
    public String name;

    @Override
    public String toString() {
        return "SupplierDto{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
