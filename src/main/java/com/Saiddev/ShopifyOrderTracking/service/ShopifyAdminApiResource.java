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
import java.util.Date;

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


    @Scheduled(fixedRate = 1000000)
    public void fetchOrders() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mp-integrator-test-2.myshopify.com/admin/api/2024-04/orders.json?status=any"))
                .header("X-Shopify-Access-Token", "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")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        OrderListDto orderListDto = gson.fromJson(response.body(), OrderListDto.class);

        for (OrderDto orderDto : orderListDto.getOrderDtos()) {
            Order order = new Order();

            order.setOrderIdOnApi(orderDto.getId());
            order.setOrderName(orderDto.getName());
            order.setContactEmail(orderDto.getContactEmail());
            order.setCreatedAt(orderDto.getCreatedAt());
            order.setTax(orderDto.getCurrentTotalTax());
            order.setTotalPrice(orderDto.getTotalPrice());
            order.setFulfilmentStatus(orderDto.getFulfilment() == null ? "unfulfilled" : orderDto.getFulfilment());
            order.setPaymentStatus(orderDto.getFinancialStatus());

            LineItem lineItem = new LineItem();

            lineItem.setOrder(order);
            for (LineItemDto lineItemDto : orderDto.getLine_items()) {
                lineItem.setLineItemIdOnApi(lineItemDto.getId());
                lineItem.setGrams(lineItemDto.getGrams());
                lineItem.setIsGiftCard(lineItemDto.getGift());
                lineItem.setProductName(lineItemDto.getName());
                lineItem.setPrice(lineItemDto.getPrice());
                lineItem.setProductId(lineItemDto.getProductId());
                lineItem.setQuantity(lineItemDto.getCurrentQuantity());

                lineItemService.saveLineItem(lineItem);
            }

            Customer customer = new Customer();
            Customer bilinmeyenMusteri = new Customer(99L, 9999L, "bilimeyen@bilinmeyen", new Date(), new Date(), "bilinmeyen", "bilinmeyen", "TRY", "+900000000000");
            Address address = new Address();
            Address bilinmeyenAddress = new Address(99L, 11111111L, "Msinan mah", "corum", "Turkey", "19000", "TRY", false, customer);

            CustomerDto customerDto = orderDto.getCustomerDtos();

            if (customerDto == null) {
                customer = bilinmeyenMusteri;
            } else {
                AddressDto addressDto = customerDto.getAddressDtos();
                if (addressDto == null) {
                    address = bilinmeyenAddress;
                } else {
                    address.setAddressIdOnApi(addressDto.getId());
                    address.setAddress(addressDto.getAddress1() + " " + addressDto.getAddress2());
                    address.setCity(addressDto.getCity());
                    address.setCountry(addressDto.getCountry());
                    address.setZip(addressDto.getZip());
                    address.setCountryCode(addressDto.getCountryCode());
                    address.setIsDefaultAddress(addressDto.getIsDefaultCustomerAddress());
                    address.setCustomer(customer);
                }
                customer.setCustomerIdOnApi(customerDto.getId());
                customer.setEmail(customerDto.getEmail());
                customer.setCreatedAt(customerDto.getCreatedAt());
                customer.setUpdatedAt(customerDto.getUpdatedAt());
                customer.setFirstName(customerDto.getFirstName());
                customer.setLastName(customerDto.getLastName());
                customer.setCurrency(customerDto.getCurrency());
                customer.setPhone(customerDto.getPhone());
            }


            customerService.saveCustomer(customer);
            addressService.saveAddress(address);
            order.setCustomer(customer);
            orderService.saveOrder(order);

        }
    }
}

