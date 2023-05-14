package com.federicogualdi.shipmenthub.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.federicogualdi.shipmenthub.entities.embeddable.Coordinate;
import com.federicogualdi.shipmenthub.entities.embeddable.InternalDate;
import com.federicogualdi.shipmenthub.entities.enums.PackageStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "package")
public class Package extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "order_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Order order;

    @Embedded
    private Coordinate destination;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private PackageStatus status;

    @Embedded
    private InternalDate internalDate;

    public static Package Create(Order order, double latitude, double longitude) {
        var aPackage = new Package();
        aPackage.setOrder(order);
        aPackage.setDestination(Coordinate.Create(latitude, longitude));
        aPackage.setInternalDate(InternalDate.Create());
        aPackage.setStatus(PackageStatus.IN_TRANSIT);

        return aPackage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Coordinate getDestination() {
        return destination;
    }

    public void setDestination(Coordinate destination) {
        this.destination = destination;
    }

    public PackageStatus status() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }

    public InternalDate getInternalDate() {
        return internalDate;
    }

    public void setInternalDate(InternalDate internalDate) {
        this.internalDate = internalDate;
    }

    @Override
    public String toString() {
        return "Package{" + "id=" + id + ", order=" + order + ", destination=" + destination + ", status=" + status + '}';
    }
}
