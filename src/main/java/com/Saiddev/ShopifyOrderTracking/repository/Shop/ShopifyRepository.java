package com.Saiddev.ShopifyOrderTracking.repository.Shop;

import com.Saiddev.ShopifyOrderTracking.entity.shop.Shopify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopifyRepository extends JpaRepository<Shopify, Long> {
    Shopify findByShopName(String shopName);
}

