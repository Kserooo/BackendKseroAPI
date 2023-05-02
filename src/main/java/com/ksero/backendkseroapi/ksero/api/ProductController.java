package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.domain.service.ProductService;
import com.ksero.backendkseroapi.ksero.mapping.ProductMapper;
import com.ksero.backendkseroapi.ksero.resources.product.CreateProductResource;
import com.ksero.backendkseroapi.ksero.resources.product.ProductResource;
import com.ksero.backendkseroapi.ksero.resources.product.UpdateProductResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@SecurityRequirement(name = "acme")
@CrossOrigin(origins = "*" , maxAge = 3600)
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER') or hasRole('WHOLESALER')")
    public List<ProductResource> getByWholesalerId(@PathVariable Long wholesalerId){
        return mapper.toResource(productService.getByWholesalerId(wholesalerId));
    }


    @GetMapping("{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER') or hasRole('WHOLESALER')")
    public ProductResource getProductById(@PathVariable Long productId){
        return mapper.toResource(productService.getById(productId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public ProductResource createProduct(@RequestBody CreateProductResource resource){
        return mapper.toResource(productService.create(resource));
    }

    @PutMapping("{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public ProductResource updateProduct(@PathVariable Long productId,
                                         @RequestBody @Valid UpdateProductResource resource, BindingResult validation){
        if(validation.hasErrors()) {
            return null;
        }
        Product product = mapper.toModel(resource);
        try {
            productService.updateProductWithWholeSalerId(resource.getWholesalerId(), product);
        } catch (Exception e) {
            return null;
        }
        return mapper.toResource(productService.update(productId, product));
    }

    @DeleteMapping("{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        return productService.delete(productId);
    }

}
