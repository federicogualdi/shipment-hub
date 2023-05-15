package com.federicogualdi.shipmenthub.api.rest.dto.converter;

import com.federicogualdi.shipmenthub.api.rest.dto.SupplierDto;
import com.federicogualdi.shipmenthub.entities.Supplier;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class SupplierConverter {

    public SupplierDto to(Supplier supplier) {
        SupplierDto dto = new SupplierDto();
        dto.id = supplier.getId();
        dto.name = supplier.getName();

        return dto;
    }
}
