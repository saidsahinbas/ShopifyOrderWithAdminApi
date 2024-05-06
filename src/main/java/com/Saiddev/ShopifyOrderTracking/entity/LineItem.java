package com.Saiddev.ShopifyOrderTracking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.math.BigDecimal;

@Entity
@Table(name = "line_item")
@Getter
@Setter
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "current_quantity")
    private Integer currentQuantity;

    @Column(name = "fulfilable_quantity")
    private Integer fulfilableQuantity;

    @Column(name = "grams")
    private Double grams;

    @Column(name = "gift_card")
    private Boolean giftCard;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "product_exist")
    private Boolean productExist;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "variant_id")
    private Long variantId;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;
}
