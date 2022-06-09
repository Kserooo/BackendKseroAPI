package com.ksero.backendkseroapi.ksero.domain.persistence;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WholesalerRepository extends JpaRepository<Wholesaler,Long> {
}
