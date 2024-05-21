package com.Saiddev.ShopifyOrderTracking.entity.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "variant")
@AllArgsConstructor
@NoArgsConstructor
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "variant_id_on_api")
    private Long variantIdOnApi;

    @Column(name = "sku")
    private String sku;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;
    //TODo
    @Column(name = "fulfillment_service")
    private String fulfilmentService;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "taxable")
    private Boolean taxable;

    @Column(name = "inventory_quantity")
    private Integer inventoryQuantity;

    //fk -> Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}
