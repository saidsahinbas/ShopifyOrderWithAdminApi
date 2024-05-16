package com.Saiddev.ShopifyOrderTracking.service;

import com.Saiddev.ShopifyOrderTracking.entity.Address;
import com.Saiddev.ShopifyOrderTracking.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address){
        return addressRepository.save(address);
    }

    public Address getAddressByIdOnApi(Long addressIdOnApi){
        return addressRepository.findByAddressIdOnApi(addressIdOnApi);
    }

    public Address findByAddressIdOnApi(Long addressIdOnApi){
        return addressRepository.findByAddressIdOnApi(addressIdOnApi);
    }
}

