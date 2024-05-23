package com.Saiddev.ShopifyOrderTracking.service;

import com.Saiddev.ShopifyOrderTracking.dto.*;
import com.Saiddev.ShopifyOrderTracking.entity.Address;
import com.Saiddev.ShopifyOrderTracking.entity.Customer;
import com.Saiddev.ShopifyOrderTracking.entity.LineItem;
import com.Saiddev.ShopifyOrderTracking.entity.Order;
import com.Saiddev.ShopifyOrderTracking.entity.shop.ShopPlatform;
import com.Saiddev.ShopifyOrderTracking.entity.shop.Shopify;
import com.Saiddev.ShopifyOrderTracking.entity.shop.Trendyol;
import com.Saiddev.ShopifyOrderTracking.service.shop.ShopPlatformService;
import com.Saiddev.ShopifyOrderTracking.service.shop.ShopifyService;
import com.Saiddev.ShopifyOrderTracking.service.shop.TrendyolService;
import com.google.gson.Gson;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

@Component
public class ShopifyAdminApiResource {

    private final Gson gson;
    private final OrderService orderService;
    private final LineItemService lineItemService;
    private final AddressService addressService;
    private final CustomerService customerService;
    private final ShopifyService shopifyService;
    private final TrendyolService trendyolService;
    private final ShopPlatformService shopPlatformService;

    public ShopifyAdminApiResource(OrderService orderService,
                                   Gson gson,
                                   LineItemService lineItemService,
                                   AddressService addressService,
                                   CustomerService customerService,
                                   ShopifyService shopifyService,
                                   TrendyolService trendyolService,
                                   ShopPlatformService shopPlatformService) {
        this.orderService = orderService;
        this.lineItemService = lineItemService;
        this.addressService = addressService;
        this.customerService = customerService;
        this.gson = gson;
        this.shopifyService = shopifyService;
        this.trendyolService = trendyolService;
        this.shopPlatformService = shopPlatformService;
    }


    @Scheduled(fixedRate = 300000)
    public void fetchOrders() throws IOException, InterruptedException {
        List<ShopPlatform> shopPlatforms = shopPlatformService.getAllShopsFromDb();
        for (ShopPlatform shopPlatform : shopPlatforms) {
            if (shopPlatform.getShopPlatformName().equals("trendyol")) {
                Trendyol trendyol = (Trendyol) shopPlatform;
                trendyolService.getOrderResponseTrendyol(trendyol.getSupplierId());
            }
            if (shopPlatform.getShopPlatformName().equals("shopify")) {
                Shopify shopify = (Shopify) shopPlatform;

                HttpResponse<String> response = shopifyService.getOrderResponseShopify(shopify.getShopName());
                OrderListDto orderListDto = gson.fromJson(response.body(), OrderListDto.class);

                for (OrderDto orderDto : orderListDto.getOrderDtos()) {
                    Order order = createOrUpdateOrderFromDto(orderDto);
                    order.setShopPlatform(shopPlatform);
                    Customer customer = createCustomerFromDto(orderDto.getCustomerDtos());
                    Address address = createAddressFromDto(orderDto.getCustomerDtos(), customer);

                    customerService.saveCustomer(customer);
                    addressService.saveAddress(address);
                    order.setCustomer(customer);
                    orderService.saveOrder(order);

                    processLineItems(orderDto, order);
                }
            }
        }
    }

    private Order createOrUpdateOrderFromDto(OrderDto orderDto) {
        Order order = orderService.findByOrderIdOnApi(orderDto.getId());
        if (order == null) {
            order = new Order();
        }
        updateOrder(order, orderDto);
        return order;
    }

    private void updateOrder(Order order, OrderDto orderDto) {
        order.setOrderIdOnApi(orderDto.getId());
        order.setOrderName(orderDto.getName());
        order.setContactEmail(orderDto.getContactEmail());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setTax(orderDto.getCurrentTotalTax());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setFulfilmentStatus(orderDto.getFulfilment() == null ? "unfulfilled" : orderDto.getFulfilment());
        order.setPaymentStatus(orderDto.getFinancialStatus());
    }

    private Customer createCustomerFromDto(CustomerDto customerDto) {
        if (customerDto == null) {
            return new Customer(1L, 9999L, "bilimeyen@bilinmeyen", new Date(), new Date(), "bilinmeyen", "bilinmeyen", "TRY");
        } else {
            Customer customer = customerService.findByCustomerIdOnApi(customerDto.getId());
            if (customer == null) {
                // Customer doesn't exist, create a new one
                customer = new Customer();
            }
            // Set customer properties
            customer.setCustomerIdOnApi(customerDto.getId());
            customer.setEmail(customerDto.getEmail());
            customer.setCreatedAt(customerDto.getCreatedAt());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setPhone(customerDto.getPhone());
            customer.setUpdatedAt(customerDto.getUpdatedAt());
            return customer;
        }
    }

    private Address createAddressFromDto(CustomerDto customerDto, Customer customer) {
        if (customerDto == null || customerDto.getAddressDtos() == null) {
            return new Address(1L, 11111111L, "Msinan mah", "corum", "Turkey", "19000", "TRY", true, customer);
        } else {
            AddressDto addressDto = customerDto.getAddressDtos();
            Address address = addressService.getAddressByIdOnApi(addressDto.getId());
            if (address == null) {
                address = new Address();
            }
            address.setAddressIdOnApi(addressDto.getId());
            address.setAddress(addressDto.getAddress1() + " " + addressDto.getAddress2());
            address.setCity(addressDto.getCity());
            address.setCountry(addressDto.getCountry());
            address.setZip(addressDto.getZip());
            address.setCountryCode(addressDto.getCountryCode());
            address.setIsDefaultAddress(addressDto.getIsDefaultCustomerAddress());
            address.setCustomer(customer);
            return address;
        }
    }


    private void processLineItems(OrderDto orderDto, Order order) {
        for (LineItemDto lineItemDto : orderDto.getLine_items()) {
            LineItem lineItem = createOrUpdateLineItemFromDto(lineItemDto, order);
            lineItemService.saveLineItem(lineItem);
        }
    }

    private LineItem createOrUpdateLineItemFromDto(LineItemDto lineItemDto, Order order) {
        LineItem lineItem = lineItemService.findLineItemByIdOnApi(lineItemDto.getId());
        if (lineItem == null) {
            lineItem = new LineItem();
        }
        updateLineItem(lineItem, lineItemDto, order);
        return lineItem;
    }

    private void updateLineItem(LineItem lineItem, LineItemDto lineItemDto, Order order) {
        lineItem.setOrder(order);
        lineItem.setLineItemIdOnApi(lineItemDto.getId());
        lineItem.setIsGiftCard(lineItemDto.getGift());
        lineItem.setPrice(lineItemDto.getPrice());
        lineItem.setProductId(lineItemDto.getProductId());
        lineItem.setQuantity(lineItemDto.getCurrentQuantity());
    }
}

