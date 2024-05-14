package com.Saiddev.ShopifyOrderTracking.service;

import com.Saiddev.ShopifyOrderTracking.entity.Order;
import com.Saiddev.ShopifyOrderTracking.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order){
        //entityManager.persist(order);
        return orderRepository.save(order);
    }

    public Page<Order> getAllOrders(Pageable pageable){

        return orderRepository.findAll(pageable);
    }

    public Optional<Order> findByOrderWithId(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getStatsBetweenDates(Date startDate, Date endDate) {
        return orderRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(startDate, endDate);
    }

}
