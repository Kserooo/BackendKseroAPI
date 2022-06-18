package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.service.WholesalerOrderService;
import com.ksero.backendkseroapi.ksero.mapping.WholesalerOrderMapper;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.CreateWholesalerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.UpdateWholesalerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.WholesalerOrderResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Page<WholesalerOrderResource> getAllWholesalerOrders(Pageable pageable) {
        return mapper.modelListPage(wholesalerOrderService.getAll(), pageable);
    }

    @GetMapping("{wholesalerOrderId}")
    public WholesalerOrderResource getWholesalerOrderById(@PathVariable Long wholesalerOrderId){
        return mapper.toResource(wholesalerOrderService.getById(wholesalerOrderId));
    }

    @PostMapping
    public WholesalerOrderResource createWholesalerOrder(@RequestBody CreateWholesalerOrderResource resource){
        return mapper.toResource(wholesalerOrderService.create(mapper.toModel(resource)));
    }

    @PutMapping("{wholesalerOrderId}")
    public WholesalerOrderResource updateWholesalerOrder(@PathVariable Long wholesalerOrderId,
                                         @RequestBody UpdateWholesalerOrderResource resource){
        return mapper.toResource(wholesalerOrderService.update(wholesalerOrderId, mapper.toModel(resource)));
    }

    @DeleteMapping("{wholesalerOrderId}")
    public ResponseEntity<?> deleteWholesaler(@PathVariable Long wholesalerOrderId){
        return wholesalerOrderService.delete(wholesalerOrderId);
    }

}
