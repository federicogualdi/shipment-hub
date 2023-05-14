package com.federicogualdi.shipmenthub.services;

import com.federicogualdi.shipmenthub.api.rest.dto.CreateOrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.UpdateOrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.converter.OrderConverter;
import com.federicogualdi.shipmenthub.api.rest.dto.converter.PackageConverter;
import com.federicogualdi.shipmenthub.entities.Depot;
import com.federicogualdi.shipmenthub.entities.Order;
import com.federicogualdi.shipmenthub.entities.Package;
import com.federicogualdi.shipmenthub.entities.embeddable.Coordinate;
import com.federicogualdi.shipmenthub.entities.enums.PackageStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    OrderConverter orderConverter;

    @Inject
    PackageConverter packageConverter;

    @Transactional
    public void createOrder(CreateOrderDto createOrderDto) {
        logger.info("Create new order with param {}", createOrderDto);

        Depot depot = Depot.findById(createOrderDto.depotId);
        var order = Order.Create(depot);
        var packages = createOrderDto.createPackageDtoList.stream().map(packageDto -> Package.Create(order, packageDto.latitude, packageDto.longitude)).collect(Collectors.toList());
        Package.persist(packages);
    }


    public Order updateOrder(UpdateOrderDto updateOrderDto, Integer orderId) {
        logger.info("Updating order {} with param {}", orderId, updateOrderDto);

        Order order = Order.findById(orderId);
        if (order.getPackages().size() != updateOrderDto.updatePackageDtoList.size()) {
            throw new BadRequestException("Updated packages cannot be removed. You can only change parameters.");
        }

        orderConverter.updateOrder(order, updateOrderDto);
        Order.persist(order);
        return order;
    }

    public List<Coordinate> plan(Integer depotId) {
        Depot depot = Depot.findById(depotId);
        List<Coordinate> coordinates = depot.getOrders().parallelStream().flatMap(o -> o.getPackages().parallelStream().filter(p -> PackageStatus.IN_TRANSIT.equals(p.status())).map(p -> p.getDestination())).collect(Collectors.toList());
        //List<Package> packages = Package.find("status", PackageStatus.IN_TRANSIT).list();
        //List<Coordinate> coordinates = new ArrayList<>(packages.stream().filter(p -> depotId.equals(p.getOrder().getDepot().getId())).map(Package::getDestination).toList());

        if (coordinates.isEmpty()) {
            return coordinates;
        }

        coordinates.add(0, depot.getCoordinate());
        coordinates.add(depot.getCoordinate());
        return coordinates;
        //return Order.find("package.status = ?1", PackageStatus.IN_TRANSIT).list();
        //return Order.find("select p.id, p.order_id, p.latitude, p.longitude, p.status, p.created_on, p.updated_on FROM `package` p join `order` where p.status = 1", Parameters.with("status", PackageStatus.IN_TRANSIT)).list();
        //return Package.find("select p.id, p.latitude, p.longitude, p.status, p.created_on, p.updated_on FROM `package` p join `order` where p.status = 1", Parameters.with("status", PackageStatus.IN_TRANSIT)).list();
    }
}
