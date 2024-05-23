package com.Saiddev.ShopifyOrderTracking.entity.shop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("TRENDYOL")
public class Trendyol extends ShopPlatform {

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "base_url")
    private String baseUrl;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_secret_key")
    private String apiSecretKey;
}
