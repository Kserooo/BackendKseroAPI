package com.ksero.backendkseroapi.ksero.mapping;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.resources.product.CreateProductResource;
import com.ksero.backendkseroapi.ksero.resources.product.ProductResource;
import com.ksero.backendkseroapi.ksero.resources.product.UpdateProductResource;
import com.ksero.backendkseroapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class ProductMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ProductResource toResource(Product model){return mapper.map(model, ProductResource.class);}

    public List<ProductResource> toResource(List<Product> model){
        return mapper.mapList(model, ProductResource.class);
    }

    public Product toModel(CreateProductResource resource){
        return mapper.map(resource, Product.class);
    }

    public Product toModel(UpdateProductResource resource){
        return mapper.map(resource, Product.class);
    }

}
