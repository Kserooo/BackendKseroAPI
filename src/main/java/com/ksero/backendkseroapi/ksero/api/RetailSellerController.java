package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSeller;
import com.ksero.backendkseroapi.ksero.domain.service.RetailSellerService;
import com.ksero.backendkseroapi.ksero.mapping.RetailSellerMapper;
import com.ksero.backendkseroapi.ksero.resources.retail_seller.CreateRetailSellerResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller.RetailSellerResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller.UpdateRetailSellerResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@CrossOrigin(origins = "*" , maxAge = 3600)
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
    public List<RetailSellerResource> getAll(){
        return mapper.toResource(retailSellerService.getAll());
    }

    @GetMapping("{retailSellerId}")
    public RetailSellerResource getRetailSellerById(@PathVariable Long retailSellerId){
        return mapper.toResource(retailSellerService.getById(retailSellerId));
    }

    @GetMapping("retailSellerUsername/{retailSellerUsername}")
    public RetailSellerResource getByRetailSellerUsername(@PathVariable String retailSellerUsername){
        RetailSeller seller = retailSellerService.getByRetailSellerUsername(retailSellerUsername);
        if(seller == null) {
            return null;
        }
        return mapper.toResource(seller);
    }

    @PostMapping
    public RetailSellerResource createRetailSeller(@RequestBody CreateRetailSellerResource resource){
        return mapper.toResource(retailSellerService.create(mapper.toModel(resource)));
    }

    @PutMapping("{retailSellerId}")
    public RetailSellerResource updateRetailSeller(@PathVariable Long retailSellerId,
                                               @RequestBody UpdateRetailSellerResource resource){
        return mapper.toResource(retailSellerService.update(retailSellerId, mapper.toModel(resource)));
    }

    @DeleteMapping("{retailSellerId}")
    public ResponseEntity<?> deleteRetailSeller(@PathVariable Long retailSellerId){
        return retailSellerService.delete(retailSellerId);
    }
}
