package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.service.RetailSellerOrderService;
import com.ksero.backendkseroapi.ksero.mapping.RetailSellerOrderMapper;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.CreateRetailSellerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.RetailSellerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.UpdateRetailSellerOrderResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/retail-seller-orders")
public class RetailSellerOrderController {

    private final RetailSellerOrderService retailSellerOrderService;
    private final RetailSellerOrderMapper mapper;

    public RetailSellerOrderController(RetailSellerOrderService retailSellerOrderService, RetailSellerOrderMapper mapper){
        this.retailSellerOrderService = retailSellerOrderService;
        this.mapper = mapper;
    }

    @GetMapping("{retailSellerOrderId}")
    public RetailSellerOrderResource getRetailSellerOrderById(@PathVariable Long retailSellerOrderId){
        return mapper.toResource(retailSellerOrderService.getById(retailSellerOrderId));
    }

    @PostMapping
    public RetailSellerOrderResource createRetailSellerOrder(@RequestBody CreateRetailSellerOrderResource resource){
        return mapper.toResource(retailSellerOrderService.create(mapper.toModel(resource)));
    }

    @PutMapping("{retailSellerOrderId}")
    public RetailSellerOrderResource updateWholesalerOrder(@PathVariable Long retailSellerOrderId,
                                                         @RequestBody UpdateRetailSellerOrderResource resource){
        return mapper.toResource(retailSellerOrderService.update(retailSellerOrderId, mapper.toModel(resource)));
    }

    @DeleteMapping("{retailSellerOrderId}")
    public ResponseEntity<?> deleteRetailSeller(@PathVariable Long retailSellerOrderId){
        return retailSellerOrderService.delete(retailSellerOrderId);
    }
}
