package com.ksero.backendkseroapi.ksero.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import com.ksero.backendkseroapi.ksero.domain.persistence.WholesalerRepository;
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
public class WholesalerServiceImpl implements WholesalerService {
    private static final String ENTITY = "Wholesaler";
    private final WholesalerRepository wholesalerRepository;
    private final Validator validator;
    public WholesalerServiceImpl(WholesalerRepository wholesalerRepository, Validator validator) {
        this.wholesalerRepository = wholesalerRepository;
        this.validator = validator;
    }

    @Override
    public List<Wholesaler> getAll() {
        return wholesalerRepository.findAll();
    }

    @Override
    public Wholesaler getById(Long wholesalerId) {
        return wholesalerRepository.findById(wholesalerId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, wholesalerId));
    }

    @Override
    public Wholesaler create(Wholesaler wholesaler) {
        Set<ConstraintViolation<Wholesaler>> violations = validator.validate(wholesaler);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        /*Student wholesalerWithName = wholesalerRepository.findByName(wholesaler.getName());

        if (wholesalerWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An retail seller with the same name already exists."); */

        return wholesalerRepository.save(wholesaler);
    }

    @Override
    public Wholesaler update(Long id, Wholesaler wholesaler) {
        Set<ConstraintViolation<Wholesaler>> violations = validator.validate(wholesaler);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

       /* Student studentWithName = studentRepository.findByName(request.getName());

        if (studentWithName != null && !studentWithName.getId().equals(studentId))
            throw new ResourceValidationException(ENTITY,
                    "An student with the same name already exists.");*/

        return wholesalerRepository.findById(id).map(existingSkill ->
                        wholesalerRepository.save(existingSkill.withId(wholesaler.getId())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long wholesalerId) {
        return wholesalerRepository.findById(wholesalerId).map(skill -> {
            wholesalerRepository.delete(skill);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, wholesalerId));
    }
}
