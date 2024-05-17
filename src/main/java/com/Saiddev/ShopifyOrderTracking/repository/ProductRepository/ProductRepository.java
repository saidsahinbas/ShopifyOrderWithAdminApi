package com.Saiddev.ShopifyOrderTracking.repository.ProductRepository;

import com.Saiddev.ShopifyOrderTracking.entity.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByProductIdOnApi(Long productId);
}
