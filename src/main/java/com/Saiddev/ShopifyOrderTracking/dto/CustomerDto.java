package com.Saiddev.ShopifyOrderTracking.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class CustomerDto {
    private Long id;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private String firstName;
    private String lastName;
    private Integer ordersCount;
    private Double totalSpent;
    private String currency;
    private String phone;

    private List<AddressDto> addressDtos;
    private List<OrderDto> orderDtos;

}
