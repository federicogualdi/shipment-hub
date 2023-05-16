package com.federicogualdi.shipmenthub.api.rest.dto;

import java.util.List;

public class CreateOrderDto {

    public int depotId;
    public List<CreatePackageDto> packages;

    @Override
    public String toString() {
        return "CreateOrderDto{" + "depotId=" + depotId + ", packages=" + packages + '}';
    }
}
