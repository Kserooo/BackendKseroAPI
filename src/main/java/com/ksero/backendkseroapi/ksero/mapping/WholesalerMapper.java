package com.ksero.backendkseroapi.ksero.mapping;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.CreateWholesalerResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.UpdateWholesalerResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.WholesalerResource;
import com.ksero.backendkseroapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class WholesalerMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public WholesalerResource toResource(Wholesaler model){
        return mapper.map(model, WholesalerResource.class);
    }

    public List<WholesalerResource> toResource(List<Wholesaler> model){
        return mapper.mapList(model, WholesalerResource.class);
    }

    public Wholesaler toModel(CreateWholesalerResource resource){
        return mapper.map(resource, Wholesaler.class);
    }

    public Wholesaler toModel(UpdateWholesalerResource resource){
        return mapper.map(resource, Wholesaler.class);
    }

}
