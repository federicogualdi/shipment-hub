package com.federicogualdi.shipmenthub.api.rest.dto.converter;

import com.federicogualdi.shipmenthub.api.rest.dto.OrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.UpdateOrderDto;
import com.federicogualdi.shipmenthub.entities.Order;
import com.federicogualdi.shipmenthub.entities.Package;
import com.federicogualdi.shipmenthub.entities.embeddable.Coordinate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderConverter {

    @Inject
    PackageConverter packageConverter;
    @Inject
    DepotConverter depotConverter;
    @Inject
    SupplierConverter supplierConverter;

    public List<OrderDto> to(List<Order> all) {

        CopyOnWriteArrayList<OrderDto> out = new CopyOnWriteArrayList<>();
        all.forEach(order -> out.add(to(order)));
        return out.parallelStream().collect(Collectors.toList());
    }

    public OrderDto to(Order order) {
        OrderDto dto = new OrderDto();
        dto.id = order.getId();
        dto.depot = depotConverter.to(order.getDepot());
        dto.supplier = supplierConverter.to(order.getSupplier());
        dto.packages = packageConverter.to(order.getPackages());

        return dto;
    }

    public void updateOrder(Order order, UpdateOrderDto updateOrderDto) {
        order.setPackages(packageConverter.updatePackage(order.getPackages(), updateOrderDto.packages));
    }

    public List<Coordinate> toPackageCoordinates(List<Order> orders, int depotId) {
        return orders.stream().filter(o -> o.getDepot().getId() == depotId).map(Order::getPackages).flatMap(List::stream).map(Package::getDestination).collect(Collectors.toList());
    }
}
