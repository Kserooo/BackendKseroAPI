package com.ksero.backendkseroapi.ksero.domain.persistence;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSellerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailSellerOrderRepository extends JpaRepository<RetailSellerOrder,Long> {
    List<RetailSellerOrder> findByRetailSellerId(Long retailSellerId);
}
