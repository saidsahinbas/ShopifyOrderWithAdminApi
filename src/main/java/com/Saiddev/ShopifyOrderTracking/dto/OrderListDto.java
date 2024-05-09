package com.Saiddev.ShopifyOrderTracking.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

public class OrderListDto {
    @SerializedName("orders")
    private List<OrderDto> orderDtos;

    public List<OrderDto> getOrderDtos() {
        return orderDtos;
    }

    public void setOrderDtos(List<OrderDto> orderDtos) {
        this.orderDtos = orderDtos;
    }
}
