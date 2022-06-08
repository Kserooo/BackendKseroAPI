package com.ksero.backendkseroapi.ksero.mapping;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Wholesaler;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.CreateWholesalerResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.UpdateWholesalerResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler.WholesalerResource;
import com.ksero.backendkseroapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class WholesalerMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public WholesalerResource toResource(Wholesaler model){
        return mapper.map(model, WholesalerResource.class);
    }

    public Page<WholesalerResource> modelListPage(List<Wholesaler> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, WholesalerResource.class),
                pageable, modelList.size());
    }

    public Wholesaler toModel(CreateWholesalerResource resource){
        return mapper.map(resource, Wholesaler.class);
    }

    public Wholesaler toModel(UpdateWholesalerResource resource){
        return mapper.map(resource, Wholesaler.class);
    }

}
