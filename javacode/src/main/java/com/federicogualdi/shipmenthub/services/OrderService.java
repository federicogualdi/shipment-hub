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
import com.federicogualdi.shipmenthub.repository.DepotRepository;
import com.federicogualdi.shipmenthub.repository.OrderRepository;
import com.federicogualdi.shipmenthub.repository.PackageRepository;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    OrderConverter orderConverter;

    @Inject
    PackageConverter packageConverter;

    @Inject
    OrderRepository orderRepository;

    @Inject
    DepotRepository depotRepository;

    @Inject
    PackageRepository packageRepository;

    @Transactional
    public void createOrder(Supplier supplier, CreateOrderDto createOrderDto) {
        logger.info("Supplier {}:{} create order with param {}", supplier.getId(), supplier.getName(), createOrderDto);

        Depot depot = depotRepository.findById(createOrderDto.depotId);
        Order order = Order.Create(supplier, depot);
        List<Package> packages = packageConverter.to(order, createOrderDto.packages);

        packageRepository.persist(packages);
    }


    public void updateOrder(Supplier supplier, Integer orderId, UpdateOrderDto updateOrderDto) {
        logger.info("Updating order {} with param {}", orderId, updateOrderDto);

        Order order = orderRepository.findById(orderId);
        if (!Objects.equals(supplier.getId(), order.getSupplier().getId())) {
            throw new ForbiddenException("User requested order updating has not the authorization to perform this action");
        }
        if (order.getPackages().size() != updateOrderDto.packages.size()) {
            throw new BadRequestException("Updated packages cannot be removed. You can only change parameters.");
        }

        orderConverter.updateOrder(order, updateOrderDto);
        orderRepository.persist(order);
    }

    public List<Coordinate> plan(Integer depotId) {
        Depot depot = depotRepository.findById(depotId);
        List<Order> orders = orderRepository.findOrdersPackageWithStatus(PackageStatus.IN_TRANSIT);
        List<Coordinate> coordinates = orderConverter.toPackageCoordinates(orders, depotId);

        // fake call to service that compute the best path through complex algorithm
        return packageConverter.toRoutePlanCoordinate(depot, coordinates);
    }

    public List<OrderDto> getOrders(Supplier supplier) {
        logger.info("Retrieving orders for supplier {}:{}", supplier.getId(), supplier.getName());
        List<Order> orders = orderRepository.findOrdersBySupplierId(supplier.getId());

        return orderConverter.to(orders);
    }
}
