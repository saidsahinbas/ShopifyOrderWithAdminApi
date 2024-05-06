package com.Saiddev.ShopifyOrderTracking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "checkout_id")
    private Long checkoutId;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "shop_money_amount")
    private String shopMoneyAmount;

    @Column(name = "shop_money_currency_code")
    private String shopMoneyCurrencyCode;

    @Column(name = "presenment_money_amount")
    private String presenmentMoneyAmount;

    @Column(name = "presenment_money_currency_code")
    private String presenmentMoneyCurrencyCode;

    @Column(name = "current_total_tax")
    private String currentTotalTax;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "Order")
    private Set<Customer> customers;


    @OneToMany(mappedBy = "Order")
    private Set<LineItem> lineItems;


    public Order(Long id, Long checkoutId, String contactEmail, Date createdAt, String shopMoneyAmount, String shopMoneyCurrencyCode, String presenmentMoneyAmount, String presenmentMoneyCurrencyCode, String currentTotalTax, String name, String phoneNumber, String totalPrice, Long userId) {
        this.id = id;
        this.checkoutId = checkoutId;
        this.contactEmail = contactEmail;
        this.createdAt = createdAt;
        this.shopMoneyAmount = shopMoneyAmount;
        this.shopMoneyCurrencyCode = shopMoneyCurrencyCode;
        this.presenmentMoneyAmount = presenmentMoneyAmount;
        this.presenmentMoneyCurrencyCode = presenmentMoneyCurrencyCode;
        this.currentTotalTax = currentTotalTax;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }


    public Order() {
    }

}
