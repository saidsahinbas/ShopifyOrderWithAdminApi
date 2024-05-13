package com.Saiddev.ShopifyOrderTracking.controller;

import com.Saiddev.ShopifyOrderTracking.entity.Customer;
import com.Saiddev.ShopifyOrderTracking.entity.Order;
import com.Saiddev.ShopifyOrderTracking.service.CustomerService;
import com.Saiddev.ShopifyOrderTracking.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService){
        this.orderService = orderService;
        this.customerService = customerService;
    }


    @GetMapping("/all-orders")
    public List<Order> getAllOrder() {
        return orderService.getAllOrders();

    }

    @GetMapping("/all-customer")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    /*
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
    */

    @GetMapping("/order")
    public Optional<Order> getCurrenciesByCurrencyName(@RequestParam(name = "id") Long id) {
        return orderService.findByOrderWithId(id);
    }

    @GetMapping("/orders")
    public List<Order> getOrdersBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate) {
        return orderService.getStatsBetweenDates(startDate, endDate);
    }
}
