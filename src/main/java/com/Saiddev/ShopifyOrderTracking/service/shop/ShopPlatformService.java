package com.Saiddev.ShopifyOrderTracking.service.shop;

import com.Saiddev.ShopifyOrderTracking.entity.shop.ShopPlatform;
import com.Saiddev.ShopifyOrderTracking.repository.Shop.ShopPlatformRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopPlatformService {
    private final ShopPlatformRepository shopPlatformRepository;

    public ShopPlatformService(ShopPlatformRepository shopPlatformRepository) {
        this.shopPlatformRepository = shopPlatformRepository;
    }

    public Optional<ShopPlatform> findShopByShopId(Long shopId) {
        return shopPlatformRepository.findById(shopId);
    }

    public List<ShopPlatform> getAllShopsFromDb() {
        return shopPlatformRepository.findAll();
    }
}
