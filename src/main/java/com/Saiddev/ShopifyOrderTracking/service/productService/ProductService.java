package com.Saiddev.ShopifyOrderTracking.service.productService;

import com.Saiddev.ShopifyOrderTracking.entity.Product.Product;
import com.Saiddev.ShopifyOrderTracking.repository.Product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product findProductWithProductId(Long productId){
        return productRepository.findProductByProductIdOnApi(productId);
    }

    public Page<Product> getAllProductsWithPagination(Pageable pageable){
        return productRepository.findAll(pageable);
    }
}
