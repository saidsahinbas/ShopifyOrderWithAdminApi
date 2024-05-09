package com.Saiddev.ShopifyOrderTracking.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Setter;


public class AddressDto {
    @SerializedName("id")
    private Long id;

    @SerializedName("address1")
    private String address1;

    @SerializedName("address2")
    private String address2;

    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    @SerializedName("zip")
    private String zip;

    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("default")
    private Boolean isDefaultCustomerAddress;

    @SerializedName("customer_id")
    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Boolean getDefaultCustomerAddress() {
        return isDefaultCustomerAddress;
    }

    public void setDefaultCustomerAddress(Boolean defaultCustomerAddress) {
        isDefaultCustomerAddress = defaultCustomerAddress;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
