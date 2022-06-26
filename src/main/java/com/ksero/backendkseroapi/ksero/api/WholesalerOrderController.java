package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.service.WholesalerOrderService;
import com.ksero.backendkseroapi.ksero.mapping.WholesalerOrderMapper;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.RetailSellerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.CreateWholesalerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.UpdateWholesalerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.WholesalerOrderResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("api/v1/wholesaler-orders")
public class WholesalerOrderController {

    private final WholesalerOrderService wholesalerOrderService;
    private final WholesalerOrderMapper mapper;

    public WholesalerOrderController(WholesalerOrderService wholesalerOrderService, WholesalerOrderMapper mapper){
        this.wholesalerOrderService = wholesalerOrderService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER') or hasRole('RETAIL_SELLER')")
    public List<WholesalerOrderResource> getAll(){
        return mapper.toResource(wholesalerOrderService.getAll());
    }

    @GetMapping("{wholesalerOrderId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public WholesalerOrderResource getWholesalerOrderById(@PathVariable Long wholesalerOrderId){
        return mapper.toResource(wholesalerOrderService.getById(wholesalerOrderId));
    }

    @GetMapping("wholesalerId/{retailSellerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER')")
    public List<WholesalerOrderResource> getByRetailSellerId(@PathVariable Long retailSellerId){
        return mapper.toResource(wholesalerOrderService.getByRetailSellerId(retailSellerId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER')")
    public WholesalerOrderResource createWholesalerOrder(@RequestBody CreateWholesalerOrderResource resource){
        return mapper.toResource(wholesalerOrderService.create(mapper.toModel(resource)));
    }

    @PutMapping("{wholesalerOrderId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER')")
    public WholesalerOrderResource updateWholesalerOrder(@PathVariable Long wholesalerOrderId,
                                         @RequestBody UpdateWholesalerOrderResource resource){
        return mapper.toResource(wholesalerOrderService.update(wholesalerOrderId, mapper.toModel(resource)));
    }

    @DeleteMapping("{wholesalerOrderId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RETAIL_SELLER') or hasRole('WHOLESALER')")
    public ResponseEntity<?> deleteWholesalerOrder(@PathVariable Long wholesalerOrderId){
        return wholesalerOrderService.delete(wholesalerOrderId);
    }

}
