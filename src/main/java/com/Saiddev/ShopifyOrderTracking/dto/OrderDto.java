package com.Saiddev.ShopifyOrderTracking.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class OrderDto {
    private Long id;
    private String contactEmail;
    private Date createdAt;
    private String name;
    private Double currentTotalTax;
    private Double totalPrice;

    private List<LineItemDto> lineItems;
}
