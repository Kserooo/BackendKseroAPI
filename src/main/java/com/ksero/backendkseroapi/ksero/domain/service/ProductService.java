package com.ksero.backendkseroapi.ksero.domain.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {


    List<Product> getAll();

    Product getById(Long productId);

    Product create(Long wholesalerId, Product product);

    Product update(Long wholesalerId, Long productId, Product product);

    ResponseEntity<?> delete(Long wholesalerId, Long productId);

}
