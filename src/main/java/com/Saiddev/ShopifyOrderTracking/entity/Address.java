package com.Saiddev.ShopifyOrderTracking.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address_id_on_api")
    private Long addressIdOnApi;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name="zip")
    private String zip;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "is_default_address")
    private Boolean isDefaultAddress;

    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

}
