package com.Saiddev.ShopifyOrderTracking.repository.ProductRepository;

import com.Saiddev.ShopifyOrderTracking.entity.Product.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByImageIdOnApi(Long imageId);
}
