package com.ksero.backendkseroapi.ksero.domain.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WholesalerService {
    List<Wholesaler> getAll();

    Page<Wholesaler> getAll(Pageable pageable);

    Wholesaler getById(Long wholesalerId);

    Wholesaler create(Wholesaler wholesaler);

    Wholesaler update(Long id, Wholesaler wholesaler);

    ResponseEntity<?> delete(Long wholesalerId);
}
