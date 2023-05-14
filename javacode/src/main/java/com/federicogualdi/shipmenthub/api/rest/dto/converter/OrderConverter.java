package com.federicogualdi.shipmenthub.api.rest.dto.converter;

import com.federicogualdi.shipmenthub.api.rest.dto.OrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.UpdateOrderDto;
import com.federicogualdi.shipmenthub.entities.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderConverter {

    @Inject
    PackageConverter packageConverter;

    public OrderDto to(Order order) {
        return null;
    }

    public void updateOrder(Order order, UpdateOrderDto updateOrderDto) {
        order.setPackages(packageConverter.updatePackage(order.getPackages(), updateOrderDto.updatePackageDtoList));
    }
}
