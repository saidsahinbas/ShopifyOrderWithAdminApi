package com.Saiddev.ShopifyOrderTracking.repository;

import com.Saiddev.ShopifyOrderTracking.entity.LineItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    Page<LineItem> findAll(Pageable pageable);

    LineItem findByLineItemIdOnApi(Long lineItemIdOnApi);
}
