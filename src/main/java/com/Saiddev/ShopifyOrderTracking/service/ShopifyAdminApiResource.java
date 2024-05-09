package com.Saiddev.ShopifyOrderTracking.service;

import com.Saiddev.ShopifyOrderTracking.dto.*;
import com.Saiddev.ShopifyOrderTracking.entity.Address;
import com.Saiddev.ShopifyOrderTracking.entity.Customer;
import com.Saiddev.ShopifyOrderTracking.entity.LineItem;
import com.Saiddev.ShopifyOrderTracking.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ShopifyAdminApiResource {

    ObjectMapper objectMapper = new ObjectMapper();

    private final Gson gson;
    private final OrderService orderService;
    private final LineItemService lineItemService;
    private final AddressService addressService;
    private final CustomerService customerService;


    public ShopifyAdminApiResource(OrderService orderService,
                                   Gson gson,
                                   LineItemService lineItemService,
                                   AddressService addressService,
                                   CustomerService customerService) {
        this.orderService = orderService;
        this.lineItemService = lineItemService;
        this.addressService = addressService;
        this.customerService = customerService;
        this.gson = gson;
    }


    @Scheduled(fixedRate = 10000000)
    public void fetchOrders() throws IOException, InterruptedException {
        Order order = new Order();
        LineItem lineItem = new LineItem();
        Customer customer = new Customer();
        Address address = new Address();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mp-integrator-test-2.myshopify.com/admin/api/2024-04/orders.json"))
                .header("X-Shopify-Access-Token", "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")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        OrderListDto orderListDto = gson.fromJson(response.body(), OrderListDto.class);

        for (OrderDto orderDto : orderListDto.getOrderDtos()) {
            order.setId(orderDto.getId());
            order.setContactEmail(orderDto.getContactEmail());
            order.setOrderCreatedAt(orderDto.getCreatedAt());
            order.setOrderName(orderDto.getName());
            order.setTax(orderDto.getCurrentTotalTax());
            order.setTotalPrice(orderDto.getTotalPrice());
            order.setFulfilmentStatus(orderDto.getFulfilment() == null ? "unfulfilled" : orderDto.getFulfilment() );
            order.setPaymentStatus(orderDto.getFinancialStatus());
            lineItem.setOrder(order);

            for (LineItemDto lineItemDto : orderDto.getLineItemDto()) {
                lineItem.setId(lineItemDto.getId());
                lineItem.setQuantity(lineItemDto.getCurrentQuantity());
                lineItem.setGrams(lineItemDto.getGrams());
                lineItem.setIsGiftCard(lineItemDto.getGift());
                lineItem.setProductName(lineItemDto.getName());
                lineItem.setPrice(lineItemDto.getPrice());
                lineItem.setProductId(lineItemDto.getProductId());
                lineItemService.saveLineItem(lineItem);
            }

            CustomerDto customerDto = orderDto.getCustomerDtos();
            customer.setId(customerDto.getId());
            customer.setEmail(customerDto.getEmail());
            customer.setCreatedAt(customerDto.getCreatedAt());
            customer.setUpdatedAt(customerDto.getUpdatedAt());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setTotalSpent(customerDto.getTotalSpent());
            customer.setCurrency(customerDto.getCurrency());
            customer.setPhone(customerDto.getPhone());

            order.setCustomer(customer);
            address.setCustomer(customer);

            AddressDto addressDto = customerDto.getAddressDtos();
            address.setId(addressDto.getId());
            address.setAddress(addressDto.getAddress1() + " " +  addressDto.getAddress2());
            address.setCity(addressDto.getCity());
            address.setCountry(addressDto.getCountry());
            address.setZip(addressDto.getZip());
            address.setCountryCode(addressDto.getCountryCode());
            address.setIsDefaultAddress(addressDto.getDefaultCustomerAddress());

            customerService.saveCustomer(customer);
            addressService.saveAddress(address);
            orderService.saveOrder(order);


        }

    }

}
