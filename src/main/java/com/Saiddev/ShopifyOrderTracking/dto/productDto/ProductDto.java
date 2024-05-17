package com.Saiddev.ShopifyOrderTracking.dto.productDto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductDto {

    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("vendor")
    private String vendor;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("published_at")
    private Date publishedAt;

    @SerializedName("variants")
    private List<VariantDto> variantDto;

    @SerializedName("images")
    private List<ImageDto> imageDto;
}
