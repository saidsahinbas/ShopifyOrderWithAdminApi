package com.Saiddev.ShopifyOrderTracking.service.productService;

import com.Saiddev.ShopifyOrderTracking.entity.Product.Product;
import com.Saiddev.ShopifyOrderTracking.repository.ProductRepository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
