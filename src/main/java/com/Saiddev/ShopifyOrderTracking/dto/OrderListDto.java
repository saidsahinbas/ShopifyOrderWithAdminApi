package com.Saiddev.ShopifyOrderTracking.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class OrderListDto {
    @SerializedName("orders")
    private List<OrderDto> orderDtos;
}
