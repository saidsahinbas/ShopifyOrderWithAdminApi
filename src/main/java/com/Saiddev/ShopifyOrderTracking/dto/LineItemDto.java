package com.Saiddev.ShopifyOrderTracking.dto;

import lombok.Data;

@Data
public class LineItemDto {
    private Long id;
    private Integer currentQuantity;
    private Double grams;
    private Boolean gift;
    private String name;
    private Double price;
    private Long productId;
}
