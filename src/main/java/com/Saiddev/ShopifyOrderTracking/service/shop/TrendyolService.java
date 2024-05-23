package com.Saiddev.ShopifyOrderTracking.service.shop;

import com.Saiddev.ShopifyOrderTracking.entity.shop.Trendyol;
import com.Saiddev.ShopifyOrderTracking.repository.Shop.TrendyolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrendyolService {

    private final TrendyolRepository trendyolRepository;
    public TrendyolService(TrendyolRepository trendyolRepository){
        this.trendyolRepository = trendyolRepository;
    }

    public List<Trendyol> getAllTrendyolUser(){
        return trendyolRepository.findAll();
    }

    public Trendyol getTrendyolUserWithSupplierId(Long supplierId){
        return trendyolRepository.findBySupplierId(supplierId);
    }

    public void getOrderResponseTrendyol(Long supplierId) {
        Trendyol trendyol = getTrendyolUserWithSupplierId(supplierId);
        System.out.println("trendyol Order isteği gerçekleşti");
        System.out.println(trendyol.getBaseUrl() +"/orders" + "Api secret key:" + trendyol.getApiSecretKey() + "supplierId:" + trendyol.getSupplierId());
    }

    public void getProductResponseTrendyol(Long supplierId) {
        Trendyol trendyol = getTrendyolUserWithSupplierId(supplierId);
        System.out.println("trendyol Product isteği gerçekleşti");
        System.out.println(trendyol.getBaseUrl() +"/products" + "Api secret key:" + trendyol.getApiSecretKey() + "supplierId:" + trendyol.getSupplierId());
    }

}
