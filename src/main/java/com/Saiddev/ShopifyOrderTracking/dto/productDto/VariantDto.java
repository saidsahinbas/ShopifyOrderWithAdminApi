package com.Saiddev.ShopifyOrderTracking.dto.productDto;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class VariantDto {

    @SerializedName("id")
    private Long id;

    @SerializedName("sku")
    private String sku;

    @SerializedName("title")
    private String title;

    @SerializedName("price")
    private Double price;

    @SerializedName("barcode")
    private String barcode;

    @SerializedName("taxable")
    private Boolean taxable;

    @SerializedName("inventory_quantity")
    private Integer inventoryQuantity;

    @SerializedName("product_id")
    private Long productId;
}
