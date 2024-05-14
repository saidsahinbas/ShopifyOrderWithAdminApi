package com.Saiddev.ShopifyOrderTracking.controller;

import com.Saiddev.ShopifyOrderTracking.entity.Customer;
import com.Saiddev.ShopifyOrderTracking.entity.LineItem;
import com.Saiddev.ShopifyOrderTracking.entity.Order;
import com.Saiddev.ShopifyOrderTracking.service.CustomerService;
import com.Saiddev.ShopifyOrderTracking.service.LineItemService;
import com.Saiddev.ShopifyOrderTracking.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private LineItemService lineItemService;

    public OrderController(OrderService orderService, CustomerService customerService, LineItemService lineItemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.lineItemService = lineItemService;
    }


    @GetMapping("/all-orders")
    public ResponseEntity<Page<Order>> getAllOrder(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderService.getAllOrders(pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @GetMapping("/all-items")
    public ResponseEntity<Page<LineItem>> getLineItems(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<LineItem> lineItems = lineItemService.getAllLineItems(pageable);
        return new ResponseEntity<>(lineItems, HttpStatus.OK);
    }

    @GetMapping("/all-customer")
    public ResponseEntity<Page<Customer>> getAllCustomer(@RequestParam(value = "page", required = false) int page,
                                                         @RequestParam(value = "size", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerService.getAllCustomer(pageable);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @GetMapping("/order")
    public Optional<Order> getCurrenciesByCurrencyName(@RequestParam(name = "id") Long id) {
        return orderService.findByOrderWithId(id);
    }

    @GetMapping("/orders-with-date")
    public List<Order> getOrdersBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate) {
        return orderService.getStatsBetweenDates(startDate, endDate);
    }
}
