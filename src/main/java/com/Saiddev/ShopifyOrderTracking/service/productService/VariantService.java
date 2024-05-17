package com.Saiddev.ShopifyOrderTracking.service.productService;

import com.Saiddev.ShopifyOrderTracking.entity.Product.Variant;
import com.Saiddev.ShopifyOrderTracking.repository.ProductRepository.VariantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VariantService {
    private final VariantRepository variantRepository;

    public VariantService(VariantRepository variantRepository){
        this.variantRepository = variantRepository;
    }

    public Variant saveVariant(Variant variant){
        return variantRepository.save(variant);
    }

    public Variant findVariantByIdOnApi(Long variantId) {
        return variantRepository.findByVariantIdOnApi(variantId);
    }
}
