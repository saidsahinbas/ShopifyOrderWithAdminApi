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
            Order order = createOrderFromDto(orderDto);
            Customer customer = createCustomerFromDto(orderDto.getCustomerDtos());
            Address address = createAddressFromDto(orderDto.getCustomerDtos(), customer);

            customerService.saveCustomer(customer);
            addressService.saveAddress(address);
            order.setCustomer(customer);
            orderService.saveOrder(order);

            processLineItems(orderDto, order);
        }
    }

    private Order createOrderFromDto(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderIdOnApi(orderDto.getId());
        order.setOrderName(orderDto.getName());
        order.setContactEmail(orderDto.getContactEmail());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setTax(orderDto.getCurrentTotalTax());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setFulfilmentStatus(orderDto.getFulfilment() == null ? "unfulfilled" : orderDto.getFulfilment());
        order.setPaymentStatus(orderDto.getFinancialStatus());
        return order;
    }

    private Customer createCustomerFromDto(CustomerDto customerDto) {
        if (customerDto == null) {
            // Customer information is missing, use placeholder customer
            return new Customer(1L, 9999L, "bilimeyen@bilinmeyen", new Date(), new Date(), "bilinmeyen", "bilinmeyen", "TRY", "+900000000000");
        } else {
            // Check if customer with the same API ID already exists
            Customer customer = customerService.findByCustomerIdOnApi(customerDto.getId());
            if (customer == null) {
                // Customer doesn't exist, create a new one
                customer = new Customer();
            }
            // Set customer properties
            customer.setCustomerIdOnApi(customerDto.getId());
            customer.setEmail(customerDto.getEmail());
            customer.setCreatedAt(customerDto.getCreatedAt());
            customer.setCurrency(customerDto.getCurrency());
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
            // Yönetilen bir duruma getir
            return address;
        }
    }

    private void processLineItems(OrderDto orderDto, Order order) {
        for (LineItemDto lineItemDto : orderDto.getLine_items()) {
            LineItem lineItem = new LineItem();
            lineItem.setOrder(order);
            lineItem.setLineItemIdOnApi(lineItemDto.getId());
            lineItem.setGrams(lineItemDto.getGrams());
            lineItem.setIsGiftCard(lineItemDto.getGift());
            lineItem.setProductName(lineItemDto.getName());
            lineItem.setPrice(lineItemDto.getPrice());
            lineItem.setProductId(lineItemDto.getProductId());
            lineItem.setQuantity(lineItemDto.getCurrentQuantity());
            lineItemService.saveLineItem(lineItem);
        }
    }
}

