package com.ksero.backendkseroapi.ksero.domain.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSellerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RetailSellerOrderService {
    List<RetailSellerOrder> getAll();
    List<RetailSellerOrder> getByRetailSellerId(Long retailSellerId);

    RetailSellerOrder getById(Long retailSellerOrderId);

    RetailSellerOrder create(RetailSellerOrder retailSellerOrder);

    RetailSellerOrder update(Long id, RetailSellerOrder retailSellerOrder);

    ResponseEntity<?> delete(Long retailSellerOrderId);

}
