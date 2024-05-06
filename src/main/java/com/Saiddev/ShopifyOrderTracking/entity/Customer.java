package com.Saiddev.ShopifyOrderTracking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "order-count")
    private Integer orderCount;

    @Column(name = "state")
    private String state;

    @Column(name = "total_spent")
    private Double totalSpent;

    @Column(name = "last_order_id")
    private Long lastOrderId;

    @Column(name = "last_order_name")
    private String lastOrderName;

    @Column(name = "currency")
    private String currency;

    @Column(name = "phone")
    private String phone;

    // TODO: Address fk to Customer
    // Many to one
    @OneToMany(mappedBy = "Customer")
    private Set<Address> addresses;

    // Order fk to Customer
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;


}
