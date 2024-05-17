package com.Saiddev.ShopifyOrderTracking.dto.productDto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
@Data
public class ImageDto {

    @SerializedName("id")
    private Long id;

    @SerializedName("alt")
    private String alt;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("width")
    private Integer width;

    @SerializedName("height")
    private Integer height;

    @SerializedName("src")
    private String imageSource;

    @SerializedName("product_id")
    private Long productId;
}
