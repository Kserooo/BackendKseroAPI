package com.ksero.backendkseroapi.ksero.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSeller;
import com.ksero.backendkseroapi.ksero.domain.model.entity.WholesalerOrder;
import com.ksero.backendkseroapi.ksero.domain.persistence.RetailSellerRepository;
import com.ksero.backendkseroapi.ksero.domain.persistence.WholesalerOrderRepository;
import com.ksero.backendkseroapi.ksero.domain.service.WholesalerOrderService;
import com.ksero.backendkseroapi.shared.exception.ResourceNotFoundException;
import com.ksero.backendkseroapi.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WholesalerOrderServiceImpl implements WholesalerOrderService {
    private static final String ENTITY = "WholesalerOrder";
    private static final String ENTITY2 = "RetailSeller";
    private final WholesalerOrderRepository wholesalerOrderRepository;
    private final RetailSellerRepository retailSellerRepository;

    private final Validator validator;

    public WholesalerOrderServiceImpl(WholesalerOrderRepository wholesalerOrderRepository, RetailSellerRepository retailSellerRepository, Validator validator) {
        this.wholesalerOrderRepository = wholesalerOrderRepository;
        this.retailSellerRepository = retailSellerRepository;
        this.validator = validator;
    }

    @Override
    public List<WholesalerOrder> getAll() {
        return wholesalerOrderRepository.findAll();
    }

    @Override
    public WholesalerOrder getById(Long wholesalerOrderId) {
        return wholesalerOrderRepository.findById(wholesalerOrderId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, wholesalerOrderId));
    }

    @Override
    public List<WholesalerOrder> getByRetailSellerId(Long retailSellerId){
        Optional<RetailSeller> retailSeller = retailSellerRepository.findById(retailSellerId);
        if(!retailSeller.isPresent())
            throw new ResourceNotFoundException(ENTITY2, retailSellerId);

        return wholesalerOrderRepository.findByRetailSellerId(retailSellerId);
    }

    @Override
    public WholesalerOrder create(WholesalerOrder wholesalerOrder) {
        Set<ConstraintViolation<WholesalerOrder>> violations = validator.validate(wholesalerOrder);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        return wholesalerOrderRepository.save(wholesalerOrder);
    }

    @Override
    public WholesalerOrder update(Long wholesalerOrderId, WholesalerOrder request) {

        Set<ConstraintViolation<WholesalerOrder>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return wholesalerOrderRepository.findById(wholesalerOrderId).map(wholesalerOrder ->
                        wholesalerOrderRepository.save(wholesalerOrder
                                .withQuantity(request.getQuantity())
                                .withRetailSeller(request.getRetailSeller())
                                .withProduct(request.getProduct())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, wholesalerOrderId));

    }

    @Override
    public ResponseEntity<?> delete(Long wholesalerOrderId) {
        return wholesalerOrderRepository.findById(wholesalerOrderId).map(wholesalerOrder -> {
            wholesalerOrderRepository.delete(wholesalerOrder);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, wholesalerOrderId));
    }
}
