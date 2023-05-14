package com.federicogualdi.shipmenthub.api.rest.dto;

import com.federicogualdi.shipmenthub.entities.embeddable.Coordinate;
import com.federicogualdi.shipmenthub.entities.enums.PackageStatus;

public class UpdatePackageDto {
    public Coordinate coordinate;
    public PackageStatus packageStatus;

    @Override
    public String toString() {
        return "UpdatePackageDto{" + "coordinate=" + coordinate + '}';
    }
}
