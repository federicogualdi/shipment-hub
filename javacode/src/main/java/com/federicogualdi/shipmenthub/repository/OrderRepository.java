package com.federicogualdi.shipmenthub.repository;

import com.federicogualdi.shipmenthub.entities.Order;
import com.federicogualdi.shipmenthub.entities.enums.PackageStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    public Order findById(Integer orderId) {
        return Order.findById(orderId);
    }

    public void persist(Order order) {
        Order.persist(order);
    }

    public List<Order> findOrdersPackageWithStatus(PackageStatus packageStatus) {
        String queryOrders = "SELECT o FROM `Order` o JOIN o.packages p WHERE p.status = :package_status";
        return Order.find(queryOrders, Parameters.with("package_status", packageStatus)).list();
    }

    public List<Order> findOrdersBySupplierId(int supplierId) {
        return Order.find("supplier.id", Sort.ascending("id"), supplierId).list();
    }

}
