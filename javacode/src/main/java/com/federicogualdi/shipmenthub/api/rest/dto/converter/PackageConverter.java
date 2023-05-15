package com.federicogualdi.shipmenthub.api.rest.dto.converter;

import com.federicogualdi.shipmenthub.api.rest.dto.CreatePackageDto;
import com.federicogualdi.shipmenthub.api.rest.dto.PackageDto;
import com.federicogualdi.shipmenthub.api.rest.dto.UpdatePackageDto;
import com.federicogualdi.shipmenthub.entities.Depot;
import com.federicogualdi.shipmenthub.entities.Order;
import com.federicogualdi.shipmenthub.entities.Package;
import com.federicogualdi.shipmenthub.entities.embeddable.Coordinate;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@ApplicationScoped
public class PackageConverter {

    public List<PackageDto> to(List<Package> all) {

        CopyOnWriteArrayList<PackageDto> out = new CopyOnWriteArrayList<>();
        all.forEach(aPackage -> out.add(to(aPackage)));
        return out.parallelStream().collect(Collectors.toList());
    }

    public PackageDto to(Package aPackage) {
        PackageDto dto = new PackageDto();
        dto.id = aPackage.getId();
        dto.latitude = aPackage.getDestination().getLatitude();
        dto.longitude = aPackage.getDestination().getLongitude();
        dto.status = aPackage.getStatus();

        return dto;
    }

    public List<Package> updatePackage(List<Package> packageList, List<UpdatePackageDto> updatePackageDtoList) {
        for (int i = 0; i < updatePackageDtoList.size(); ++i) {
            Package current = packageList.get(i);
            UpdatePackageDto updated = updatePackageDtoList.get(i);
            if (Objects.nonNull(updated.coordinate)) current.setDestination(updated.coordinate);
            if (Objects.nonNull(updated.status)) current.setStatus(updated.status);
        }
        return packageList;
    }

    public List<Package> to(Order order, List<CreatePackageDto> createPackageDtoList) {
        return createPackageDtoList.stream().map(packageDto -> Package.Create(order, packageDto.latitude, packageDto.longitude)).collect(Collectors.toList());
    }

    public List<Coordinate> toRoutePlanCoordinate(Depot depot, List<Coordinate> packagesCoordinates){
        if (packagesCoordinates.isEmpty()) {
            return new ArrayList<>();
        }

        List<Coordinate> routeCoordinate = new ArrayList<>(packagesCoordinates);
        routeCoordinate.add(0, depot.getCoordinate());
        routeCoordinate.add(depot.getCoordinate());
        return routeCoordinate;
    }
}
