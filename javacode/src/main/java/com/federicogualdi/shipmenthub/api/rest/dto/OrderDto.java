package com.federicogualdi.shipmenthub.api.rest.dto;

import com.federicogualdi.shipmenthub.entities.Package;

import java.util.List;

public class OrderDto {

    public Integer id;
    public List<PackageDto> packageDtoList;
}
