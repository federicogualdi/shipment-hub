package com.federicogualdi.shipmenthub.api.rest.dto;

import java.util.List;

public class UpdateOrderDto {
    public Integer depotId;
    public List<UpdatePackageDto> packages;

    @Override
    public String toString() {
        return "UpdateOrderDto{" + "depotId=" + depotId + ", packages=" + packages + '}';
    }
}
