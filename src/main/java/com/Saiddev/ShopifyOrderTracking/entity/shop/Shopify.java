package com.Saiddev.ShopifyOrderTracking.entity.shop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("SHOPIFY")
public class Shopify extends ShopPlatform {

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_secret")
    private String apiSecret;

    @Column(name = "base_url")
    private String baseUrl;

    @Column(name = "access_token")
    private String accessToken;
}
