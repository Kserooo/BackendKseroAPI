package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.service.RetailSellerService;
import com.ksero.backendkseroapi.ksero.mapping.RetailSellerMapper;
import com.ksero.backendkseroapi.ksero.resources.retail_seller.CreateRetailSellerResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller.RetailSellerResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller.UpdateRetailSellerResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/retail-sellers")
public class RetailSellerController {
    private final RetailSellerService retailSellerService;
    private final RetailSellerMapper mapper;

    public RetailSellerController(RetailSellerService retailSellerService, RetailSellerMapper mapper){
        this.retailSellerService = retailSellerService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<RetailSellerResource> getAll(){
        return mapper.toResource(retailSellerService.getAll());
    }

    @GetMapping("{retailSellerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public RetailSellerResource getRetailSellerById(@PathVariable Long retailSellerId){
        return mapper.toResource(retailSellerService.getById(retailSellerId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public RetailSellerResource createRetailSeller(@RequestBody CreateRetailSellerResource resource){
        return mapper.toResource(retailSellerService.create(mapper.toModel(resource)));
    }

    @PutMapping("{retailSellerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public RetailSellerResource updateRetailSeller(@PathVariable Long retailSellerId,
                                               @RequestBody UpdateRetailSellerResource resource){
        return mapper.toResource(retailSellerService.update(retailSellerId, mapper.toModel(resource)));
    }

    @DeleteMapping("{retailSellerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> deleteRetailSeller(@PathVariable Long retailSellerId){
        return retailSellerService.delete(retailSellerId);
    }
}
