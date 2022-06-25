package com.ksero.backendkseroapi.ksero.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSellerOrder;
import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import com.ksero.backendkseroapi.ksero.domain.model.entity.WholesalerOrder;
import com.ksero.backendkseroapi.ksero.domain.persistence.ProductRepository;
import com.ksero.backendkseroapi.ksero.domain.persistence.WholesalerOrderRepository;
import com.ksero.backendkseroapi.ksero.domain.persistence.WholesalerRepository;
import com.ksero.backendkseroapi.ksero.domain.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    private static final String ENTITY = "Product";
    private static final String ENTITY2 = "Wholesaler";

    private final ProductRepository productRepository;
    private final WholesalerRepository wholesalerRepository;

    private final Validator validator;

    public ProductServiceImpl(ProductRepository productRepository, WholesalerRepository wholesalerRepository, Validator validator) {

        this.productRepository = productRepository;
        this.wholesalerRepository = wholesalerRepository;

        this.validator = validator;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, productId));
    }

    @Override
    public List<Product> getByWholesalerId(Long wholesalerId) {
        Optional<Wholesaler> wholesaler = wholesalerRepository.findById(wholesalerId);
        if(!wholesaler.isPresent())
            throw new ResourceNotFoundException(ENTITY2, wholesalerId);

        return productRepository.findByWholesalerId(wholesalerId);
    }

    @Override
    public Product create(Product product) {
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        return productRepository.save(product);
    }

    @Override
    public Product update(Long productId, Product request) {



        return productRepository.findById(productId).map(product ->
                        productRepository.save(product
                                .withName(request.getName())
                                .withDescription(request.getDescription())
                                .withWholesaler(request.getWholesaler())
                                .withPrice(request.getPrice())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, productId));

    }

    @Override
    public ResponseEntity<?> delete(Long productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, productId));
    }
}
