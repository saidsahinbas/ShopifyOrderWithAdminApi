package com.Saiddev.ShopifyOrderTracking.dto;

import com.Saiddev.ShopifyOrderTracking.entity.LineItem;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    @SerializedName("id")
    private Long id;

    @SerializedName("contact_email")
    private String contactEmail;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("name")
    private String name;

    @SerializedName("current_total_tax")
    private Double currentTotalTax;

    @SerializedName("total_price")
    private Double totalPrice;

    @SerializedName("fulfillment_status")
    private String fulfilment;

    @SerializedName("financial_status")
    private String financialStatus;

    @SerializedName("line_items")
    private List<LineItemDto> line_items;

    @SerializedName("customer")
    private CustomerDto customerDtos;
}
