package com.ksero.backendkseroapi.ksero.domain.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSellerOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RetailSellerOrderService {
    List<RetailSellerOrder> getAll();
    RetailSellerOrder getById(Long retailSellerOrderId);

    List<RetailSellerOrder> getByRetailSellerId(Long retailSellerId);

    RetailSellerOrder create(RetailSellerOrder retailSellerOrder);

    RetailSellerOrder update(Long id, RetailSellerOrder retailSellerOrder);

    ResponseEntity<?> delete(Long retailSellerOrderId);

}
