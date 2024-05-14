package com.Saiddev.ShopifyOrderTracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.math.BigDecimal;

@Entity
@Table(name = "line_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "line_item_id_on_api")
    private Long lineItemIdOnApi;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "grams")
    private Double grams;

    @Column(name = "is_gift_card")
    private Boolean isGiftCard;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Double price;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order order;
}
