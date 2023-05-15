package com.federicogualdi.shipmenthub.services;

import com.federicogualdi.shipmenthub.api.rest.dto.CreateOrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.OrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.UpdateOrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.converter.OrderConverter;
import com.federicogualdi.shipmenthub.api.rest.dto.converter.PackageConverter;
import com.federicogualdi.shipmenthub.entities.Depot;
import com.federicogualdi.shipmenthub.entities.Order;
import com.federicogualdi.shipmenthub.entities.Package;
import com.federicogualdi.shipmenthub.entities.Supplier;
import com.federicogualdi.shipmenthub.entities.embeddable.Coordinate;
import com.federicogualdi.shipmenthub.entities.enums.PackageStatus;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    OrderConverter orderConverter;

    @Inject
    PackageConverter packageConverter;

    @Transactional
    public void createOrder(Supplier supplier, CreateOrderDto createOrderDto) {
        logger.info("Supplier {}:{} create order with param {}", supplier.getId(), supplier.getName(), createOrderDto);

        Depot depot = Depot.findById(createOrderDto.depotId);
        Order order = Order.Create(supplier, depot);
        List<Package> packages = packageConverter.to(order, createOrderDto.packages);

        Package.persist(packages);
    }


    public void updateOrder(Supplier supplier, Integer orderId, UpdateOrderDto updateOrderDto) {
        logger.info("Updating order {} with param {}", orderId, updateOrderDto);

        Order order = Order.findById(orderId);
        if (!Objects.equals(supplier.getId(), order.getSupplier().getId())) {
            throw new ForbiddenException("User requested order updating has not the authorization to perform this action");
        }
        if (order.getPackages().size() != updateOrderDto.packages.size()) {
            throw new BadRequestException("Updated packages cannot be removed. You can only change parameters.");
        }

        orderConverter.updateOrder(order, updateOrderDto);
        Order.persist(order);
    }

    public List<Coordinate> plan(Integer depotId) {
        Depot depot = Depot.findById(depotId);
        String queryOrders = "SELECT o FROM `Order` o JOIN o.packages p WHERE p.status = :package_status";
        List<Order> orders = Order.find(queryOrders, Parameters.with("package_status", PackageStatus.IN_TRANSIT)).list();
        List<Coordinate> coordinates = orders.stream().filter(o -> o.getDepot().getId() == depotId).map(Order::getPackages).flatMap(List::stream).map(Package::getDestination).collect(Collectors.toList());

        // fake call to service that compute the best path through complex algorithm
        return packageConverter.toRoutePlanCoordinate(depot, coordinates);
    }

    public List<OrderDto> getOrders(Supplier supplier) {
        logger.info("Retrieving orders for supplier {}:{}", supplier.getId(), supplier.getName());
        List<Order> orders = Order.find("supplier.id", Sort.ascending("id"), supplier.getId()).list();

        return orderConverter.to(orders);
    }
}
