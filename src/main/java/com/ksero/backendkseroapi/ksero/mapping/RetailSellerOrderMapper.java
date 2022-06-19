package com.ksero.backendkseroapi.ksero.mapping;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSellerOrder;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.CreateRetailSellerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.RetailSellerOrderResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller_order.UpdateRetailSellerOrderResource;
import com.ksero.backendkseroapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class RetailSellerOrderMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public List<RetailSellerOrderResource> toResource(List<RetailSellerOrder> model){
        return mapper.mapList(model, RetailSellerOrderResource.class);
    }

    public RetailSellerOrderResource toResource(RetailSellerOrder model){
        return mapper.map(model, RetailSellerOrderResource.class);
    }

    public RetailSellerOrder toModel(CreateRetailSellerOrderResource resource){
        return mapper.map(resource, RetailSellerOrder.class);
    }

    public RetailSellerOrder toModel(UpdateRetailSellerOrderResource resource){
        return mapper.map(resource, RetailSellerOrder.class);
    }

}
