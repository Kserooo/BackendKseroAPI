package com.ksero.backendkseroapi.ksero.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSeller;
import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSellerOrder;
import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import com.ksero.backendkseroapi.ksero.domain.persistence.RetailSellerOrderRepository;
import com.ksero.backendkseroapi.ksero.domain.persistence.RetailSellerRepository;
import com.ksero.backendkseroapi.ksero.domain.service.RetailSellerOrderService;
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
public class RetailSellerOrderServiceImpl implements RetailSellerOrderService {
    private static final String ENTITY = "RetailSellerOrder";
    private static final String ENTITY2 = "RetailSeller";
    private final RetailSellerOrderRepository retailSellerOrderRepository;
    private final RetailSellerRepository retailSellerRepository;
    private final Validator validator;
    public RetailSellerOrderServiceImpl(RetailSellerOrderRepository retailSellerOrderRepository, RetailSellerRepository retailSellerRepository, Validator validator) {
        this.retailSellerOrderRepository = retailSellerOrderRepository;
        this.retailSellerRepository = retailSellerRepository;
        this.validator = validator;
    }

    @Override
    public List<RetailSellerOrder> getAll() {
        return retailSellerOrderRepository.findAll();
    }

    @Override
    public RetailSellerOrder getById(Long retailSellerOrderId) {
        return retailSellerOrderRepository.findById(retailSellerOrderId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, retailSellerOrderId));
    }

    @Override
    public List<RetailSellerOrder> getByRetailSellerId(Long retailSellerId){
        Optional<RetailSeller> retailSeller = retailSellerRepository.findById(retailSellerId);
        if(!retailSeller.isPresent())
            throw new ResourceNotFoundException(ENTITY2, retailSellerId);

        return retailSellerOrderRepository.findByRetailSellerId(retailSellerId);
    }
    @Override
    public RetailSellerOrder create(RetailSellerOrder retailSellerOrder) {
        Set<ConstraintViolation<RetailSellerOrder>> violations = validator.validate(retailSellerOrder);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        return retailSellerOrderRepository.save(retailSellerOrder);
    }

    @Override
    public RetailSellerOrder update(Long retailSellerOrderId, RetailSellerOrder request) {

        Set<ConstraintViolation<RetailSellerOrder>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return retailSellerOrderRepository.findById(retailSellerOrderId).map(retailSellerOrder ->
                        retailSellerOrderRepository.save(retailSellerOrder
                                .withQuantity(request.getQuantity())
                                .withRetailSeller(request.getRetailSeller())
                                .withProduct(request.getProduct())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, retailSellerOrderId));
    }

    @Override
    public ResponseEntity<?> delete(Long retailSellerOrderId) {
        return retailSellerOrderRepository.findById(retailSellerOrderId).map(retailSellerOrder -> {
            retailSellerOrderRepository.delete(retailSellerOrder);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, retailSellerOrderId));
    }
}
