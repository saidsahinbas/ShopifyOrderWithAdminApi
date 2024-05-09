package com.Saiddev.ShopifyOrderTracking.dto;


import com.google.gson.annotations.SerializedName;

public class LineItemDto {
    private Long id;
    @SerializedName("current_quantity")
    private Integer currentQuantity;
    @SerializedName("grams")
    private Double grams;
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

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public void setGrams(Double grams) {
        this.grams = grams;
    }

    public void setGift(Boolean gift) {
        this.gift = gift;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public Double getGrams() {
        return grams;
    }

    public Boolean getGift() {
        return gift;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Long getProductId() {
        return productId;
    }
}
