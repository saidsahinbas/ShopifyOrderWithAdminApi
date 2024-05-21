package com.Saiddev.ShopifyOrderTracking.repository.Product;

import com.Saiddev.ShopifyOrderTracking.entity.Product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.domain.Pageable;
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Product findProductByProductIdOnApi(Long productId);

}
