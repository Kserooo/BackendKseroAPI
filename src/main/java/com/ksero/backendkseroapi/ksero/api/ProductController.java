package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.service.ProductService;
import com.ksero.backendkseroapi.ksero.mapping.ProductMapper;
import com.ksero.backendkseroapi.ksero.resources.product.CreateProductResource;
import com.ksero.backendkseroapi.ksero.resources.product.ProductResource;
import com.ksero.backendkseroapi.ksero.resources.product.UpdateProductResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER') or hasRole('WHOLESALER')")
    public List<ProductResource> getAll(){
        return mapper.toResource(productService.getAll());
    }

    @GetMapping("wholesalerId/{wholesalerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER')")
    public List<ProductResource> getByWholesalerId(@PathVariable Long wholesalerId){
        return mapper.toResource(productService.getByWholesalerId(wholesalerId));
    }


    @GetMapping("{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER')")
    public ProductResource getProductById(@PathVariable Long productId){
        return mapper.toResource(productService.getById(productId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public ProductResource createProduct(@RequestBody CreateProductResource resource){
        return mapper.toResource(productService.create(mapper.toModel(resource)));
    }

    @PutMapping("{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public ProductResource updateProduct(@PathVariable Long productId,
                                         @RequestBody UpdateProductResource resource){
        return mapper.toResource(productService.update(productId, mapper.toModel(resource)));
    }

    @DeleteMapping("{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        return productService.delete(productId);
    }

}
