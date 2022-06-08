package com.ksero.backendkseroapi.ksero.mapping;

import com.ksero.backendkseroapi.ksero.domain.model.entity.RetailSeller;
import com.ksero.backendkseroapi.ksero.resources.retail_seller.CreateRetailSellerResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller.RetailSellerResource;
import com.ksero.backendkseroapi.ksero.resources.retail_seller.UpdateRetailSellerResource;
import com.ksero.backendkseroapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RetailSellerMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public RetailSellerResource toResource(RetailSeller model) {
        return mapper.map(model, RetailSellerResource.class);
    }

    public Page<RetailSellerResource> modelListPage(List<RetailSeller> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, RetailSellerResource.class),
                pageable, modelList.size());
    }

    public RetailSeller toModel(CreateRetailSellerResource resource) {
        return mapper.map(resource, RetailSeller.class);
    }

    public RetailSeller toModel(UpdateRetailSellerResource resource) {
        return mapper.map(resource, RetailSeller.class);
    }
}
