package com.federicogualdi.shipmenthub.api.rest.dto;

import java.util.List;

public class UpdateOrderDto {
    public List<UpdatePackageDto> updatePackageDtoList;

    @Override
    public String toString() {
        return "UpdateOrderDto{" + "updatePackageDtoList=" + updatePackageDtoList + '}';
    }
}
