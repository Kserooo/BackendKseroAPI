package com.ksero.backendkseroapi.ksero.api;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import com.ksero.backendkseroapi.ksero.domain.service.WholesalerService;
import com.ksero.backendkseroapi.ksero.mapping.WholesalerMapper;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.CreateWholesalerResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.UpdateWholesalerResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.WholesalerResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*" , maxAge = 3600)
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public List<WholesalerResource> getAll(){
        return mapper.toResource(wholesalerService.getAll());
    }

    @GetMapping("{wholesalerId}")
    public WholesalerResource getWholesalerById(@PathVariable Long wholesalerId){
        return mapper.toResource(wholesalerService.getById(wholesalerId));
    }

    @GetMapping("wholesalerUsername/{wholesalerUsername}")
    public WholesalerResource getByWholesalerUsername(@PathVariable String wholesalerUsername){
        Wholesaler wholesaler = wholesalerService.getByWholesalerUsername(wholesalerUsername);
        return mapper.toResource(wholesaler);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public WholesalerResource createWholesaler(@RequestBody CreateWholesalerResource resource){
        return mapper.toResource(wholesalerService.create(mapper.toModel(resource)));
    }

    @PutMapping("{wholesalerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public WholesalerResource updateWholesaler(@PathVariable Long wholesalerId,
                                         @RequestBody UpdateWholesalerResource resource){
        return mapper.toResource(wholesalerService.update(wholesalerId, mapper.toModel(resource)));
    }

    @DeleteMapping("{wholesalerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WHOLESALER')")
    public ResponseEntity<?> deleteWholesaler(@PathVariable Long wholesalerId){
        return wholesalerService.delete(wholesalerId);
    }
}
