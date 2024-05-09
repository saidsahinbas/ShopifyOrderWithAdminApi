package com.Saiddev.ShopifyOrderTracking.service;

import com.Saiddev.ShopifyOrderTracking.entity.Order;
import com.Saiddev.ShopifyOrderTracking.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;

    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){

        return orderRepository.findAll();
    }




    //get Order with id

    //get order with address
    //etc...
}
