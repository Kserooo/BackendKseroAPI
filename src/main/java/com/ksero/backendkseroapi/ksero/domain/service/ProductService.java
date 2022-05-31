package com.ksero.backendkseroapi.ksero.domain.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAllByWholesalerId(Long wholesalerId);

    Page<Product> getAllByWholesalerId(Long wholesalerId, Pageable pageable);

    Product create(Long wholesalerId, Product product);

    Product update(Long wholesalerId, Long productId, Product product);

    ResponseEntity<?> delete(Long wholesalerId, Long productId);
}
