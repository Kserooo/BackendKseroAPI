package com.ksero.backendkseroapi.ksero.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSellerOrder;
import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import com.ksero.backendkseroapi.ksero.domain.model.entity.WholesalerOrder;
import com.ksero.backendkseroapi.ksero.domain.persistence.RetailSellerOrderRepository;
import com.ksero.backendkseroapi.ksero.domain.persistence.WholesalerOrderRepository;
import com.ksero.backendkseroapi.ksero.domain.service.WholesalerOrderService;
import com.ksero.backendkseroapi.ksero.domain.service.WholesalerService;
import com.ksero.backendkseroapi.shared.exception.ResourceNotFoundException;
import com.ksero.backendkseroapi.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class WholesalerOrderServiceImpl implements WholesalerOrderService {
    private static final String ENTITY = "WholesalerOrder";

    private final WholesalerOrderRepository wholesalerOrderRepository;

    private final Validator validator;

    public WholesalerOrderServiceImpl(WholesalerOrderRepository wholesalerOrderRepository, Validator validator) {

        this.wholesalerOrderRepository = wholesalerOrderRepository;

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
