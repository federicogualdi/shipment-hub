package com.federicogualdi.shipmenthub.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.federicogualdi.shipmenthub.entities.embeddable.Coordinate;
import com.federicogualdi.shipmenthub.entities.embeddable.InternalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "depot")
public class Depot extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Coordinate coordinate;

    @OneToMany(mappedBy = "depot", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Order> orders;

    @Embedded
    private InternalDate internalDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public InternalDate getInternalDate() {
        return internalDate;
    }

    public void setInternalDate(InternalDate internalDate) {
        this.internalDate = internalDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Depot{" + "id=" + id + ", name='" + name + '\'' + ", coordinate=" + coordinate + ", orders=" + orders + ", internalDate=" + internalDate + '}';
    }
}
