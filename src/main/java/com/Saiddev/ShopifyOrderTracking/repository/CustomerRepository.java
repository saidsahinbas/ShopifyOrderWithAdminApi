package com.Saiddev.ShopifyOrderTracking.repository;

import com.Saiddev.ShopifyOrderTracking.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findAll(Pageable pageable);
    Customer findByCustomerIdOnApi(Long customerId);
}
