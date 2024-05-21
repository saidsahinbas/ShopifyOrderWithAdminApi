package com.Saiddev.ShopifyOrderTracking.service.productService;

import com.Saiddev.ShopifyOrderTracking.dto.productDto.*;
import com.Saiddev.ShopifyOrderTracking.entity.Product.Image;
import com.Saiddev.ShopifyOrderTracking.entity.Product.Product;
import com.Saiddev.ShopifyOrderTracking.entity.Product.Variant;
import com.Saiddev.ShopifyOrderTracking.entity.shop.Shop;
import com.Saiddev.ShopifyOrderTracking.service.shop.ShopService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class ShopifyProductApiResource {

    private final Gson gson;
    private final ProductService productService;

    private final VariantService variantService;

    private final ImageService imageService;

    private final ShopService shopService;

    public ShopifyProductApiResource(ProductService productService,
                                     VariantService variantService,
                                     ImageService imageService,
                                     Gson gson,
                                     ShopService shopService) {
        this.productService = productService;
        this.variantService = variantService;
        this.imageService = imageService;
        this.gson = gson;
        this.shopService = shopService;
    }


    @Scheduled(fixedRate = 300000)
    public void fetchProducts() throws IOException, InterruptedException {
        List<Shop> shops = shopService.getAllShopFromDb();
        for (Shop shop : shops) {
            String shopName = shop.getShopName();
            HttpResponse<String> response = shopService.getProductResponseFromDifferentShop(shopName);
            ProductListDto productListDto = gson.fromJson(response.body(), ProductListDto.class);

            for (ProductDto productDto : productListDto.getProductDtos()) {
                Product product = createOrUpdateProductFromDto(productDto);
                product.setShop(shop);
                productService.saveProduct(product);

                processImages(productDto, product);
                processVariants(productDto, product);
            }
        }
    }

    private Product createOrUpdateProductFromDto(ProductDto productDto) {
        Product product = productService.findProductWithProductId(productDto.getId());
        if (product == null) {
            product = new Product();
        }
        updateProduct(product, productDto);
        return product;
    }

    private void updateProduct(Product product, ProductDto productDto) {
        product.setProductIdOnApi(productDto.getId());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());
        product.setTitle(productDto.getTitle());
        product.setVendor(productDto.getVendor());
    }


    private void processImages(ProductDto productDto, Product product) {
        for (ImageDto imageDto : productDto.getImageDto()) {
            Image image = createOrUpdateImageFromDto(imageDto, product);
            imageService.saveImage(image);
        }
    }

    private Image createOrUpdateImageFromDto(ImageDto imageDto, Product product) {
        Image image = imageService.findImageByIdOnApi(imageDto.getId());
        if (image == null) {
            image = new Image();
        }
        updateImage(image, imageDto, product);
        return image;
    }

    private void updateImage(Image image, ImageDto imageDto, Product product) {
        image.setProduct(product);
        image.setImageIdOnApi(imageDto.getId());
        image.setImageSource(imageDto.getImageSource());
        image.setHeight(imageDto.getHeight());
        image.setWidth(imageDto.getWidth());
        image.setCreatedAt(imageDto.getCreatedAt());
        image.setUpdatedAt(imageDto.getUpdatedAt());
    }


    private void processVariants(ProductDto productDto, Product product) {
        for (VariantDto variantDto : productDto.getVariantDto()) {
            Variant variant = createOrUpdateVariantFromDto(variantDto, product);
            variantService.saveVariant(variant);
        }
    }

    private Variant createOrUpdateVariantFromDto(VariantDto variantDto, Product product) {
        Variant variant = variantService.findVariantByIdOnApi(variantDto.getId());
        if (variant == null) {
            variant = new Variant();
        }
        updateVariant(variant, variantDto, product);
        return variant;
    }

    private void updateVariant(Variant variant, VariantDto variantDto, Product product) {
        variant.setProduct(product);
        variant.setVariantIdOnApi(variantDto.getId());
        variant.setTitle(variantDto.getTitle());
        variant.setSku(variantDto.getSku());
        variant.setBarcode(variantDto.getBarcode());
        variant.setInventoryQuantity(variantDto.getInventoryQuantity());
        variant.setPrice(variantDto.getPrice());
        variant.setTaxable(variantDto.getTaxable());
    }

}
