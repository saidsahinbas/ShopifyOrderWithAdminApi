package com.Saiddev.ShopifyOrderTracking.repository.Shop;

import com.Saiddev.ShopifyOrderTracking.entity.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByShopName(String shopName);
}
