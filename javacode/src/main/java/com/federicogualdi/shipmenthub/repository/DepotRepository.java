package com.federicogualdi.shipmenthub.repository;

import com.federicogualdi.shipmenthub.entities.Depot;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepotRepository implements PanacheRepository<Depot> {

    public Depot findById(Integer depotId) {
        return Depot.findById(depotId);
    }

}
