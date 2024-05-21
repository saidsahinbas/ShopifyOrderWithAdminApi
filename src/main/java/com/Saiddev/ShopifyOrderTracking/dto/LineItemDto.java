package com.Saiddev.ShopifyOrderTracking.dto;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class LineItemDto {

    @SerializedName("id")
    private Long id;

    @SerializedName("current_quantity")
    private Integer currentQuantity;

    @SerializedName("gift_card")
    private Boolean gift;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Double price;

    @SerializedName("product_id")
    private Long productId;

    @SerializedName("order_id")
    private Long orderId;
}
