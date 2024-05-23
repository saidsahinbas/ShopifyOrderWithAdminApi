package com.Saiddev.ShopifyOrderTracking.service.shop;

import com.Saiddev.ShopifyOrderTracking.entity.shop.Shopify;
import com.Saiddev.ShopifyOrderTracking.repository.Shop.ShopifyRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ShopifyService {

    private final ShopifyRepository shopifyRepository;

    public ShopifyService(ShopifyRepository shopifyRepository) {
        this.shopifyRepository = shopifyRepository;
    }

    public List<Shopify> getAllShopifyUser() {
        return shopifyRepository.findAll();
    }

    public Shopify findShopByShopName(String shopName) {
        return shopifyRepository.findByShopName(shopName);
    }

    public HttpResponse<String> getOrderResponseShopify(String shopName) throws IOException, InterruptedException {
            Shopify shopify = findShopByShopName(shopName);
            String baseUrl = shopify.getBaseUrl() + "orders.json?status=any";
            String header = shopify.getAccessToken();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl))
                    .header("X-Shopify-Access-Token", header)
                    .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getProductResponseShopify(String shopName) throws IOException, InterruptedException {
        Shopify shopify = findShopByShopName(shopName);
        String baseUrl = shopify.getBaseUrl() + "products.json";
        String header = shopify.getAccessToken();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header("X-Shopify-Access-Token", header)
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
