package com.ksero.backendkseroapi.ksero.domain.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.domain.model.entity.WholesalerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WholesalerOrderService {

    List<WholesalerOrder> getAll();

    WholesalerOrder getById(Long wholesalerOrderId);

    WholesalerOrder create(WholesalerOrder wholesalerOrder);

    WholesalerOrder update(Long id, WholesalerOrder wholesalerOrder);

    ResponseEntity<?> delete(Long wholesalerOrderId);
}
