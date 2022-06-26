package com.ksero.backendkseroapi.ksero.domain.persistence;

import com.ksero.backendkseroapi.ksero.domain.model.entity.WholesalerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WholesalerOrderRepository extends JpaRepository<WholesalerOrder,Long> {
    List<WholesalerOrder> findByRetailSellerId(Long retailSellerId);
}
