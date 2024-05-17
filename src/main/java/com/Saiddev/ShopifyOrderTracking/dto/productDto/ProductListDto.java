package com.Saiddev.ShopifyOrderTracking.dto.productDto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ProductListDto {
    @SerializedName("products")
    private List<ProductDto> productDtos;
}
