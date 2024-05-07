package com.Saiddev.ShopifyOrderTracking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "total_price")
    private Double totalPrice;

    //tdo
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<LineItem> lineItems;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
