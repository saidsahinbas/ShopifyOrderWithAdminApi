package com.Saiddev.ShopifyOrderTracking.entity.Product;

import com.Saiddev.ShopifyOrderTracking.entity.shop.ShopPlatform;
import com.Saiddev.ShopifyOrderTracking.entity.shop.Shopify;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id_on_api")
    private Long productIdOnApi;

    @Column(name = "title")
    private String title;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Variant> variants = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "shop_platform_id", referencedColumnName = "id")
    private ShopPlatform shopPlatform;
}
