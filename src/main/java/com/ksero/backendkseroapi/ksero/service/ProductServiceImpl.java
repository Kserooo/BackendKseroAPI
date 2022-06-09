package com.ksero.backendkseroapi.ksero.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.domain.service.ProductService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(Long productId) {
        return null;
    }

    @Override
    public Product create(Long wholesalerId, Product product) {
        return null;
    }

    @Override
    public Product update(Long wholesalerId, Long productId, Product product) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long wholesalerId, Long productId) {
        return null;
    }
}
