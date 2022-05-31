package com.ksero.backendkseroapi.ksero.domain.persistence;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByWholesalerId(Long wholesalerId);

    Page<Product> findByWholesalerId(Long wholesalerId, Pageable pageable);

    Optional<Product> findByIdAndWholesalerId(Long id, Long wholesalerId);

}
