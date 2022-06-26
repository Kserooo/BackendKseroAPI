package com.ksero.backendkseroapi.ksero.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSeller;
import com.ksero.backendkseroapi.ksero.domain.persistence.RetailSellerRepository;
import com.ksero.backendkseroapi.ksero.domain.service.RetailSellerService;
import com.ksero.backendkseroapi.shared.exception.ResourceNotFoundException;
import com.ksero.backendkseroapi.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Service
public class RetailSellerServiceImpl implements RetailSellerService {
    private static final String ENTITY = "RetailSeller";
    private final RetailSellerRepository retailSellerRepository;
    private final Validator validator;
    public RetailSellerServiceImpl(RetailSellerRepository retailSellerRepository, Validator validator) {
        this.retailSellerRepository = retailSellerRepository;
        this.validator = validator;
    }

    @Override
    public List<RetailSeller> getAll() {
        return retailSellerRepository.findAll();
    }

    @Override
    public RetailSeller getById(Long retailSellerId) {
        return retailSellerRepository.findById(retailSellerId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, retailSellerId));
    }

    @Override
    public RetailSeller getByRetailSellerUsername(String retailSellerUsername){
        return retailSellerRepository.findByUsername(retailSellerUsername);
    }

    @Override
    public RetailSeller create(RetailSeller retailSeller) {
        Set<ConstraintViolation<RetailSeller>> violations = validator.validate(retailSeller);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return retailSellerRepository.save(retailSeller);
    }

    @Override
    public RetailSeller update(Long retailSellerId, RetailSeller request) {
        Set<ConstraintViolation<RetailSeller>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        return retailSellerRepository.findById(retailSellerId).map(retailSeller ->
                        retailSellerRepository.save(retailSeller
                                .withFirstName(request.getFirstName())
                                .withAddress(request.getAddress())
                                .withLastName(request.getLastName())
                                .withBirthday(request.getBirthday())
                                .withPhone(request.getPhone())
                                .withEmail(request.getEmail())
                                .withUsername(request.getUsername())
                                .withPassword(request.getPassword())
                                .withDescription(request.getDescription())
                                .withPaymentName(request.getPaymentName())
                                .withPaymentPhone(request.getPaymentPhone())
                                .withPaymentEmail(request.getPaymentEmail())
                                .withPaymentCardNumber(request.getPaymentCardNumber())
                                .withPaymentExpirationDate(request.getPaymentExpirationDate())
                                .withPaymentCVV(request.getPaymentCVV())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, retailSellerId));
    }

    @Override
    public ResponseEntity<?> delete(Long retailSellerId) {
        return retailSellerRepository.findById(retailSellerId).map(retailSeller -> {
            retailSellerRepository.delete(retailSeller);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, retailSellerId));
    }
}
