package com.federicogualdi.shipmenthub.api.rest;


import com.federicogualdi.shipmenthub.api.rest.dto.CreateOrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.OrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.UpdateOrderDto;
import com.federicogualdi.shipmenthub.entities.Order;
import com.federicogualdi.shipmenthub.entities.Package;
import com.federicogualdi.shipmenthub.entities.Supplier;
import com.federicogualdi.shipmenthub.entities.embeddable.Coordinate;
import com.federicogualdi.shipmenthub.services.AuthService;
import com.federicogualdi.shipmenthub.services.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderRest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    AuthService authService;

    @Inject
    OrderService orderService;

    @POST
    public void createOrder(CreateOrderDto createOrderDto) {
        logger.trace("Requested order creation");
        // call to authService that given a valid JWT token it will retrieve Supplier data
        Supplier supplier = authService.getSupplier("FAKE_JWT");
        orderService.createOrder(supplier, createOrderDto);
    }

    @PUT
    @Path("/{order_id}")
    public void updateOrder(@PathParam("order_id") Integer orderId, UpdateOrderDto updateOrderDto) {
        logger.trace("Requested order updating {}", orderId);
        // call to authService that given a valid JWT token it will retrieve Supplier data
        Supplier supplier = authService.getSupplier("FAKE_JWT");
        orderService.updateOrder(supplier, orderId, updateOrderDto);
    }

    @POST
    @Path("/plan/{depot_id}")
    public List<Coordinate> plan(@PathParam("depot_id") Integer depotId) {
        logger.trace("Requested route planning");
        return orderService.plan(depotId);
    }

    @GET
    public List<OrderDto> getOrders() {
        logger.trace("Requested orders retrieving");
        // call to authService that given a valid JWT token it will retrieve Supplier data
        Supplier supplier = authService.getSupplier("FAKE_JWT");
        return orderService.getOrders(supplier);
    }
}
