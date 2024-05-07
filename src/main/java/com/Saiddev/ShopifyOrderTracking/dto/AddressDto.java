package com.Saiddev.ShopifyOrderTracking.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String address1;
    private String address2;
    private String city;
    private String Country;
    private String zip;
    private String countryCode;
    private Boolean defaultCustomerAddress;
}
