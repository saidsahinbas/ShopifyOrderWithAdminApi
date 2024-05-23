package com.Saiddev.ShopifyOrderTracking.entity.shop;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@Table(name = "shop_platform")
@DiscriminatorColumn(name = "platform_type")
public abstract class ShopPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shop_platform_name")
    private String shopPlatformName;
}
