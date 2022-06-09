package com.ksero.backendkseroapi.ksero.domain.persistence;

import com.ksero.backendkseroapi.ksero.domain.model.entity.WholesalerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WholesalerOrderRepository extends JpaRepository<WholesalerOrder,Long> {
}
