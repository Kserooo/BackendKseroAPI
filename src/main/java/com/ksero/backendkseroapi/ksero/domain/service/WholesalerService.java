package com.ksero.backendkseroapi.ksero.domain.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WholesalerService {
    List<Wholesaler> getAll();

    Wholesaler getById(Long wholesalerId);
    Wholesaler create(Wholesaler wholesaler);

    Wholesaler update(Long id, Wholesaler wholesaler);

    ResponseEntity<?> delete(Long wholesalerId);
}
