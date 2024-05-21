package com.Saiddev.ShopifyOrderTracking.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class CustomerDto {
    @SerializedName("id")
    private Long id;

    @SerializedName("email")
    private String email;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("phone")
    private String phone;

    @SerializedName("default_address")
    private AddressDto addressDtos;

    @SerializedName("orders")
    private List<OrderDto> orderDtos;
}
