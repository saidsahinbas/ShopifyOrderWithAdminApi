package com.Saiddev.ShopifyOrderTracking.service;

import com.Saiddev.ShopifyOrderTracking.entity.Customer;
import com.Saiddev.ShopifyOrderTracking.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }


    public Page<Customer> getAllCustomer(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    public Customer findByCustomerIdOnApi(Long customerId) {
        return customerRepository.findByCustomerIdOnApi(customerId);
    }
}
