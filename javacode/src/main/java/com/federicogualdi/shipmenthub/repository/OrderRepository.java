package com.federicogualdi.shipmenthub.repository;

import com.federicogualdi.shipmenthub.entities.Order;
import com.federicogualdi.shipmenthub.entities.enums.PackageStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
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

    public List<Order> findOrdersBySupplierId(int supplierId, String orderBy, String orderDirection, Integer skip, Integer top) {
        return this.findOrders("supplier.id", orderBy, orderDirection, skip, top, supplierId);
    }

    private List<Order> findOrders(String query, String orderBy, String orderDirection, Integer skip, Integer top, Object... params) {

        Sort.Direction direction = Sort.Direction.Ascending;
        if (orderDirection != null && orderDirection.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.Descending;
        }

        List<Order> entities = Order.find(query, Sort.by(orderBy, direction).and("id", Sort.Direction.Ascending), 1).list();

        int startIndex = skip != null ? skip : 0;
        int endIndex = top != null ? Math.min(startIndex + top, entities.size()) : entities.size();

        if (startIndex > entities.size()) {
            return new ArrayList<>();
        }

        return entities.subList(startIndex, endIndex);
    }

}
