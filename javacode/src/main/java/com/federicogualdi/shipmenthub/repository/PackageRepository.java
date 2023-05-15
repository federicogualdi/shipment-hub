package com.federicogualdi.shipmenthub.repository;

import com.federicogualdi.shipmenthub.entities.Package;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PackageRepository implements PanacheRepository<Package> {

    public void persist(List<Package> packages) {
        Package.persist(packages);
    }

}
