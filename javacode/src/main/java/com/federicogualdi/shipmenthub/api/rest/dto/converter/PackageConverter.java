package com.federicogualdi.shipmenthub.api.rest.dto.converter;

import com.federicogualdi.shipmenthub.api.rest.dto.PackageDto;
import com.federicogualdi.shipmenthub.api.rest.dto.UpdatePackageDto;
import com.federicogualdi.shipmenthub.entities.Package;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@ApplicationScoped
public class PackageConverter {

    public List<PackageDto> to(List<Package> all) {

        CopyOnWriteArrayList<PackageDto> out = new CopyOnWriteArrayList<>();
        all.parallelStream().forEach(aPackage -> out.add(to(aPackage)));
        return out.parallelStream()
                .collect(Collectors.toList());
    }

    public List<Package> from(List<PackageDto> all) {

        CopyOnWriteArrayList<Package> out = new CopyOnWriteArrayList<>();
        all.parallelStream().forEach(aPackage -> out.add(from(aPackage)));
        return out.parallelStream()
                .collect(Collectors.toList());
    }

    public PackageDto to(Package aPackage) {
        return null;
    }

    public Package from(PackageDto packageDto) {
        return null;
    }

    public List<Package> updatePackage(List<Package> packageList, List<UpdatePackageDto> updatePackageDtoList) {
        for (int i = 0; i < updatePackageDtoList.size(); ++i) {
            Package current = packageList.get(i);
            UpdatePackageDto updated = updatePackageDtoList.get(i);
            if (Objects.nonNull(updated.coordinate))
                current.setDestination(updated.coordinate);
            if (Objects.nonNull(updated.packageStatus))
                current.setStatus(updated.packageStatus);
        }
        return packageList;
    }
}
