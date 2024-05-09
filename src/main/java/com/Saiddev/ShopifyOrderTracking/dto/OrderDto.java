package com.Saiddev.ShopifyOrderTracking.dto;

import com.Saiddev.ShopifyOrderTracking.entity.LineItem;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;


public class OrderDto {

    @SerializedName("id")
    private Long id;

    @SerializedName("contact_email")
    private String contactEmail;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("name")
    private String name;

    @SerializedName("current_total_tax")
    private Double currentTotalTax;

    @SerializedName("total_price")
    private Double totalPrice;

    @SerializedName("fulfillment_status")
    private String fulfilment;

    @SerializedName("financial_status")
    private String financialStatus;

    @SerializedName("line_items")
    private List<LineItemDto> lineItemDto;

    @SerializedName("customer")
    private CustomerDto customerDtos;

    public String getFinancialStatus() {
        return financialStatus;
    }

    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    public String getFulfilment() {
        return fulfilment;
    }

    public void setFulfilment(String fulfilment) {
        this.fulfilment = fulfilment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentTotalTax() {
        return currentTotalTax;
    }

    public void setCurrentTotalTax(Double currentTotalTax) {
        this.currentTotalTax = currentTotalTax;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<LineItemDto> getLineItemDto() {
        return lineItemDto;
    }

    public void setLineItemDto(List<LineItemDto> lineItemDto) {
        this.lineItemDto = lineItemDto;
    }

    public CustomerDto getCustomerDtos() {
        return customerDtos;
    }

    public void setCustomerDtos(CustomerDto customerDtos) {
        this.customerDtos = customerDtos;
    }
}
