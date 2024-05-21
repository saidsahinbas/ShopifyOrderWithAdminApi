package com.Saiddev.ShopifyOrderTracking.repository.ProductRepository;

import com.Saiddev.ShopifyOrderTracking.entity.Product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.domain.Pageable;
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Product findProductByProductIdOnApi(Long productId);

}
