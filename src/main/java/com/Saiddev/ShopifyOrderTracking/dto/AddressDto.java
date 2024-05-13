package com.Saiddev.ShopifyOrderTracking.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Setter;

@Data
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
}
