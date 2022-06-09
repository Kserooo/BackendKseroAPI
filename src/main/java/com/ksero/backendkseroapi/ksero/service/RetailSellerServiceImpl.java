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
    public RetailSeller create(RetailSeller retailSeller) {
        Set<ConstraintViolation<RetailSeller>> violations = validator.validate(retailSeller);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        /*Student retailSellerWithName = retailSellerRepository.findByName(retailSeller.getName());

        if (retailSellerWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An retail seller with the same name already exists."); */

        return retailSellerRepository.save(retailSeller);
    }

    @Override
    public RetailSeller update(Long id, RetailSeller retailSeller) {
        Set<ConstraintViolation<RetailSeller>> violations = validator.validate(retailSeller);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

       /* Student studentWithName = studentRepository.findByName(request.getName());

        if (studentWithName != null && !studentWithName.getId().equals(studentId))
            throw new ResourceValidationException(ENTITY,
                    "An student with the same name already exists.");*/

        return retailSellerRepository.findById(id).map(existingRetail ->
                        retailSellerRepository.save(existingRetail.withId(retailSeller.getId())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long retailSellerId) {
        return retailSellerRepository.findById(retailSellerId).map(skill -> {
            retailSellerRepository.delete(skill);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, retailSellerId));
    }
}
