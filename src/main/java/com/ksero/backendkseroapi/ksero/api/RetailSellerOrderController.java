package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.service.RetailSellerOrderService;
import com.ksero.backendkseroapi.ksero.mapping.RetailSellerOrderMapper;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.CreateRetailSellerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.RetailSellerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.UpdateRetailSellerOrderResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("api/v1/retail-seller-orders")
public class RetailSellerOrderController {
    private final RetailSellerOrderService retailSellerOrderService;
    private final RetailSellerOrderMapper mapper;

    public RetailSellerOrderController(RetailSellerOrderService retailSellerOrderService, RetailSellerOrderMapper mapper){
        this.retailSellerOrderService = retailSellerOrderService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER') or hasRole('WHOLESALER')")
    public List<RetailSellerOrderResource> getAll(){
        return mapper.toResource(retailSellerOrderService.getAll());
    }


    @GetMapping("{retailSellerOrderId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER')")
    public RetailSellerOrderResource getRetailSellerOrderById(@PathVariable Long retailSellerOrderId){
        return mapper.toResource(retailSellerOrderService.getById(retailSellerOrderId));
    }

    @GetMapping("retailSellerId/{retailSellerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER')")
    public List<RetailSellerOrderResource> getByRetailSellerId(@PathVariable Long retailSellerId){
        return mapper.toResource(retailSellerOrderService.getByRetailSellerId(retailSellerId));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public RetailSellerOrderResource createRetailSellerOrder(@RequestBody CreateRetailSellerOrderResource resource){
        return mapper.toResource(retailSellerOrderService.create(mapper.toModel(resource)));
    }

    @PutMapping("{retailSellerOrderId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public RetailSellerOrderResource updateWholesalerOrder(@PathVariable Long retailSellerOrderId,
                                                         @RequestBody UpdateRetailSellerOrderResource resource){
        return mapper.toResource(retailSellerOrderService.update(retailSellerOrderId, mapper.toModel(resource)));
    }

    @DeleteMapping("{retailSellerOrderId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER') or hasRole('WHOLESALER')")
    public ResponseEntity<?> deleteRetailSellerOrder(@PathVariable Long retailSellerOrderId){
        return retailSellerOrderService.delete(retailSellerOrderId);
    }
}
