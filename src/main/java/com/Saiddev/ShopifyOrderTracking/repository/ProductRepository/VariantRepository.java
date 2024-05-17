package com.Saiddev.ShopifyOrderTracking.repository.ProductRepository;

import com.Saiddev.ShopifyOrderTracking.entity.Product.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Long> {
    Variant findByVariantIdOnApi(Long variantId);
}
