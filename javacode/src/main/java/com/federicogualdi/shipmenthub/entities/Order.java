package com.federicogualdi.shipmenthub.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.federicogualdi.shipmenthub.entities.embeddable.InternalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Package> packages;

    @JoinColumn(name = "depot_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Depot depot;

    @JoinColumn(name = "supplier_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Supplier supplier;

    @Embedded
    private InternalDate internalDate;

    public static Order Create(Supplier supplier, Depot depot) {
        Order order = new Order();
        order.setDepot(depot);
        order.setSupplier(supplier);
        order.setPackages(new ArrayList<>());
        order.setInternalDate(InternalDate.Create());

        return order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public InternalDate getInternalDate() {
        return internalDate;
    }

    public void setInternalDate(InternalDate internalDate) {
        this.internalDate = internalDate;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", packages=" + packages + ", depotId=" + depot.getId() + ", supplierId=" + supplier.getId() + ", internalDate=" + internalDate + '}';
    }
}
