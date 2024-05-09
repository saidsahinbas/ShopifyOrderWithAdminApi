package com.Saiddev.ShopifyOrderTracking.service;

import com.Saiddev.ShopifyOrderTracking.dto.LineItemDto;
import com.Saiddev.ShopifyOrderTracking.entity.LineItem;
import com.Saiddev.ShopifyOrderTracking.repository.LineItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineItemService {
    private final LineItemRepository lineItemRepository;

    public LineItemService(LineItemRepository lineItemRepository){
        this.lineItemRepository = lineItemRepository;
    }

    public LineItem saveLineItem(LineItem lineItem){
        return lineItemRepository.save(lineItem);
    }

    public List<LineItem> getAllLineItems(){
        return lineItemRepository.findAll();
    }

}
