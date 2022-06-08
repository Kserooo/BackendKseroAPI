package com.ksero.backendkseroapi.ksero.domain.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSeller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RetailSellerService {
    List<RetailSeller> getAll();

    RetailSeller getById(Long retailSellerId);

    RetailSeller create(RetailSeller retailSeller);

    RetailSeller update(Long id, RetailSeller retailSeller);

    ResponseEntity<?> delete(Long retailSellerId);
}
