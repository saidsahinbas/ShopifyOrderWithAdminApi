package com.Saiddev.ShopifyOrderTracking.repository;

import com.Saiddev.ShopifyOrderTracking.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByAddressIdOnApi(Long addressIdOnApi);
}
