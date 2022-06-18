package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.service.ProductService;
import com.ksero.backendkseroapi.ksero.mapping.ProductMapper;
import com.ksero.backendkseroapi.ksero.resources.product.CreateProductResource;
import com.ksero.backendkseroapi.ksero.resources.product.ProductResource;
import com.ksero.backendkseroapi.ksero.resources.product.UpdateProductResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper){
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ProductResource> getAllProducts(Pageable pageable) {
        return mapper.modelListPage(productService.getAll(), pageable);
    }

    @GetMapping
    public List<ProductResource> getAll(){
        return mapper.toResource(productService.getAll());
    }
    @GetMapping("{productId}")
    public ProductResource getProductById(@PathVariable Long productId){
        return mapper.toResource(productService.getById(productId));
    }

    @PostMapping
    public ProductResource createProduct(@RequestBody CreateProductResource resource){
        return mapper.toResource(productService.create(mapper.toModel(resource)));
    }

    @PutMapping("{productId}")
    public ProductResource updateProduct(@PathVariable Long productId,
                                         @RequestBody UpdateProductResource resource){
        return mapper.toResource(productService.update(productId, mapper.toModel(resource)));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        return productService.delete(productId);
    }

}
