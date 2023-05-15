package com.federicogualdi.shipmenthub.api.rest.dto.converter;

import com.federicogualdi.shipmenthub.api.rest.dto.DepotDto;
import com.federicogualdi.shipmenthub.entities.Depot;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class DepotConverter {

    public DepotDto to(Depot depot) {
        DepotDto dto = new DepotDto();
        dto.id = depot.getId();
        dto.latitude = depot.getCoordinate().getLatitude();
        dto.longitude = depot.getCoordinate().getLongitude();

        return dto;
    }
}
