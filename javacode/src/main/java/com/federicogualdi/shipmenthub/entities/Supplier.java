package com.federicogualdi.shipmenthub.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.federicogualdi.shipmenthub.entities.embeddable.InternalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
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
        return "Supplier{" + "id=" + id + ", name='" + name + '\'' + ", orders=" + orders + ", internalDate=" + internalDate + '}';
    }
}
