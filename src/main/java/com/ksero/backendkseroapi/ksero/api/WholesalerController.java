package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.service.WholesalerService;
import com.ksero.backendkseroapi.ksero.mapping.WholesalerMapper;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.CreateWholesalerResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.UpdateWholesalerResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.WholesalerResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/wholesalers")
public class WholesalerController {

    private final WholesalerService wholesalerService;
    private final WholesalerMapper mapper;

    public WholesalerController(WholesalerService wholesalerService, WholesalerMapper mapper){
        this.wholesalerService = wholesalerService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<WholesalerResource> getAll(){
        return mapper.toResource(wholesalerService.getAll());
    }

    @GetMapping("{wholesalerId}")
    public WholesalerResource getWholesalerById(@PathVariable Long wholesalerId){
        return mapper.toResource(wholesalerService.getById(wholesalerId));
    }

    @PostMapping
    public WholesalerResource createWholesaler(@RequestBody CreateWholesalerResource resource){
        return mapper.toResource(wholesalerService.create(mapper.toModel(resource)));
    }

    @PutMapping("{wholesalerId}")
    public WholesalerResource updateWholesaler(@PathVariable Long wholesalerId,
                                         @RequestBody UpdateWholesalerResource resource){
        return mapper.toResource(wholesalerService.update(wholesalerId, mapper.toModel(resource)));
    }

    @DeleteMapping("{wholesalerId}")
    public ResponseEntity<?> deleteWholesaler(@PathVariable Long wholesalerId){
        return wholesalerService.delete(wholesalerId);
    }
}
