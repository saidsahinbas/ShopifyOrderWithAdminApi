package com.Saiddev.ShopifyOrderTracking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "order1")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "order_created_at")
    private Date orderCreatedAt;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "fulfillment_status")
    private String fulfilmentStatus;

    @Column(name = "payment_status")
    private String paymentStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<LineItem> lineItems = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


}
