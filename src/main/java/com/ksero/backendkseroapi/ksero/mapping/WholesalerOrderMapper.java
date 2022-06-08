package com.ksero.backendkseroapi.ksero.mapping;

import com.ksero.backendkseroapi.ksero.domain.model.entity.WholesalerOrder;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.CreateWholesalerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.UpdateWholesalerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.wholesaler_order.WholesalerOrderResource;
import com.ksero.backendkseroapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class WholesalerOrderMapper {

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

    public Page<WholesalerOrderResource> modelListPage(List<WholesalerOrder> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, WholesalerOrderResource.class), pageable, modelList.size());
    }

}
