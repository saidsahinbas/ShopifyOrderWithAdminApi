package com.Saiddev.ShopifyOrderTracking.service;

import com.Saiddev.ShopifyOrderTracking.entity.Order;
import com.Saiddev.ShopifyOrderTracking.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;

    public OrderService(OrderRepository orderRepository, EntityManager entityManager){
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;

    }

    public Order saveOrder(Order order){
        //entityManager.persist(order);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){

        return orderRepository.findAll();
    }

    public Optional<Order> findByOrderWithId(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getStatsBetweenDates(Date startDate, Date endDate) {
        return orderRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(startDate, endDate);
    }


    //get Order with id

    //get order with address
    //etc...
}
