package com.ksero.backendkseroapi.ksero.mapping;

import com.ksero.backendkseroapi.ksero.domain.model.entity.WholesalerOrder;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.CreateWholesalerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.UpdateWholesalerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.WholesalerOrderResource;
import com.ksero.backendkseroapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class WholesalerOrderMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public WholesalerOrderResource toResource(WholesalerOrder model){
        return mapper.map(model, WholesalerOrderResource.class);
    }

    public WholesalerOrder toModel(CreateWholesalerOrderResource resource){
        return mapper.map(resource, WholesalerOrder.class);
    }

    public WholesalerOrder toModel(UpdateWholesalerOrderResource resource){
        return mapper.map(resource, WholesalerOrder.class);
    }

    public List<WholesalerOrderResource> toResource(List<WholesalerOrder> model){
        return mapper.mapList(model, WholesalerOrderResource.class);
    }

}
