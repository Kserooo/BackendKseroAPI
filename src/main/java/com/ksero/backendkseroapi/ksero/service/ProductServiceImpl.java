package com.ksero.backendkseroapi.ksero.service;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import com.ksero.backendkseroapi.ksero.domain.persistence.ProductRepository;
import com.ksero.backendkseroapi.ksero.domain.persistence.WholesalerRepository;
import com.ksero.backendkseroapi.ksero.domain.service.ProductService;
import com.ksero.backendkseroapi.ksero.mapping.ProductMapper;
import com.ksero.backendkseroapi.ksero.resources.product.CreateProductResource;
import com.ksero.backendkseroapi.shared.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String ENTITY = "Product";
    private static final String ENTITY2 = "Wholesaler";

    private final ProductRepository productRepository;
    private final WholesalerRepository wholesalerRepository;

    private final ProductMapper productMapper;

    private final Validator validator;

    public ProductServiceImpl(ProductRepository productRepository, WholesalerRepository wholesalerRepository, ProductMapper productMapper, Validator validator) {

        this.productRepository = productRepository;
        this.wholesalerRepository = wholesalerRepository;
        this.productMapper = productMapper;

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
    public Product create(CreateProductResource product) {
        //Optional<Wholesaler> wholesalerOptional = wholesalerRepository.findById(product.getWholesaler().getId());

        Optional<Wholesaler> wholesalerOptional = wholesalerRepository.findById(product.getWholesalerId());

        if(!wholesalerOptional.isPresent()){
            throw new ResourceNotFoundException(ENTITY2, product.getWholesalerId());
        }

        Product new_product = productMapper.toModel(product);
        new_product.setId(null);
        new_product.setWholesaler(wholesalerOptional.get()); //set manual de wholesaler id

        return productRepository.save(new_product);
    }

    @Override
    public Product update(Long productId, Product request) {

        return productRepository.findById(productId).map(product ->
                        productRepository.save(product
                                .withName(request.getName())
                                .withDescription(request.getDescription())
                                .withWholesaler(request.getWholesaler())
                                .withPrice(request.getPrice())
                                .withImage(request.getImage())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, productId));

    }

    @Override
    public ResponseEntity<?> delete(Long productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, productId));
    }

    @Override
    public void updateProductWithWholeSalerId(Long wholeSalerId, Product product) throws ResourceNotFoundException {
        Optional<Wholesaler> existingWholesaler = this.wholesalerRepository.findById(wholeSalerId);
        if(existingWholesaler.isEmpty()) {
            throw new ResourceNotFoundException("Wholesaler", wholeSalerId);
        }
        product.setWholesaler(existingWholesaler.get());
    }
}
