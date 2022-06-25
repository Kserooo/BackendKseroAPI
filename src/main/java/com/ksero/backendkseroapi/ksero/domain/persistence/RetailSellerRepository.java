package com.ksero.backendkseroapi.ksero.domain.persistence;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetailSellerRepository extends JpaRepository<RetailSeller,Long> {

}
