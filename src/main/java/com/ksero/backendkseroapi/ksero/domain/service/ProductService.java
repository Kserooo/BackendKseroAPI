package com.ksero.backendkseroapi.ksero.domain.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {


    List<Product> getAll();
    List<Product> getByWholesalerId(Long wholesalerId);

    Product getById(Long productId);

    Product create(Product product);

    Product update(Long id, Product product);

    ResponseEntity<?> delete(Long productId);

}
