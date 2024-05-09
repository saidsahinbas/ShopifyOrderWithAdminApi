package com.Saiddev.ShopifyOrderTracking.controller;

import com.Saiddev.ShopifyOrderTracking.entity.Order;
import com.Saiddev.ShopifyOrderTracking.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    @GetMapping("/all-orders")
    public List<Order> getAllOrder() {
        return orderService.getAllOrders();

    }

    /*
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
    */
}
