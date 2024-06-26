package com.Saiddev.ShopifyOrderTracking.repository;

import com.Saiddev.ShopifyOrderTracking.entity.Order;
import lombok.extern.java.Log;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, CrudRepository<Order, Long> {
    List<Order> findByFulfilmentStatus(@Param("name") String name);

    Page<Order> findAll(Pageable pageable);

    List<Order> findByCreatedAtBetweenOrderByCreatedAtDesc(Date startDate, Date endDate);

    Order findByOrderIdOnApi(Long orderIdOnApi);

}
