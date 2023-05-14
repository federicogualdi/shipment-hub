package com.federicogualdi.shipmenthub.api.rest;


import com.federicogualdi.shipmenthub.api.rest.dto.CreateOrderDto;
import com.federicogualdi.shipmenthub.api.rest.dto.UpdateOrderDto;
import com.federicogualdi.shipmenthub.entities.Order;
import com.federicogualdi.shipmenthub.entities.Package;
import com.federicogualdi.shipmenthub.entities.embeddable.Coordinate;
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
    OrderService orderService;

    @POST
    public void createOrder(CreateOrderDto createOrderDto) {
        orderService.createOrder(createOrderDto);
    }

    @PUT
    @Path("/{order_id}")
    public Order updateOrder(@PathParam("order_id") Integer orderId, UpdateOrderDto updateOrderDto) {
        logger.debug("Requested updating order {}", orderId);
        return orderService.updateOrder(updateOrderDto, orderId);
    }

    @POST
    @Path("/plan/{depot_id}")
    public List<Coordinate> plan(@PathParam("depot_id") Integer depotId) {
        logger.debug("Requested route planning");
        return orderService.plan(depotId);
    }
}
