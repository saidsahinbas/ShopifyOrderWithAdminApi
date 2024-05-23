package com.Saiddev.ShopifyOrderTracking.repository.Shop;

import com.Saiddev.ShopifyOrderTracking.entity.shop.Trendyol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrendyolRepository extends JpaRepository<Trendyol, Long> {
    Trendyol findBySupplierId(Long supplierId);

}
