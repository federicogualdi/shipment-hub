package com.federicogualdi.shipmenthub.api.rest.dto;

import java.util.List;

public class OrderDto {

    public int id;
    public DepotDto depot;
    public SupplierDto supplier;
    public List<PackageDto> packages;

    @Override
    public String toString() {
        return "OrderDto{" + "id=" + id + ", depot=" + depot + ", supplier=" + supplier + ", packages=" + packages + '}';
    }
}
