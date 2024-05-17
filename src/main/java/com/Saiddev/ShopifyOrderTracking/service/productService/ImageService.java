package com.Saiddev.ShopifyOrderTracking.service.productService;

import com.Saiddev.ShopifyOrderTracking.entity.Product.Image;
import com.Saiddev.ShopifyOrderTracking.repository.ProductRepository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    public Image saveImage(Image image){
        return imageRepository.save(image);
    }

    public Image findImageByIdOnApi(Long imageId) {
        return imageRepository.findByImageIdOnApi(imageId);
    }

}
