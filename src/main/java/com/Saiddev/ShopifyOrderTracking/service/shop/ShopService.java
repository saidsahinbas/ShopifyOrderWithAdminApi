package com.Saiddev.ShopifyOrderTracking.service.shop;

import com.Saiddev.ShopifyOrderTracking.entity.shop.Shop;
import com.Saiddev.ShopifyOrderTracking.repository.Shop.ShopRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ShopService {
    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Shop findShopByShopName(String shopName) {
        return shopRepository.findByShopName(shopName);
    }

    public List<Shop> getAllShopFromDb() {
        return shopRepository.findAll();
    }

    public String getShopsInfo() {
        List<Shop> shops = getAllShopFromDb();
        String shopName = null;
        for (Shop shop : shops) {
            shopName = shop.getShopName();
            return shopName;
        }
        return shopName;
    }

    public HttpResponse<String> getOrderResponseFromDifferentShop(String shopName) throws IOException, InterruptedException {
        Shop shop = findShopByShopName(shopName);
        String baseUrl = shop.getBaseUrl() + "orders.json?status=any";
        String header = shop.getAccessToken();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header("X-Shopify-Access-Token", header)
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getProductResponseFromDifferentShop(String shopName) throws IOException, InterruptedException {
        Shop shop = findShopByShopName(shopName);
        String baseUrl = shop.getBaseUrl() + "products.json";
        String header = shop.getAccessToken();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header("X-Shopify-Access-Token", header)
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
