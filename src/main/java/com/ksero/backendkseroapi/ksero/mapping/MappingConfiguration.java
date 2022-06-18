package com.ksero.backendkseroapi.ksero.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration("kseroMappingConfiguration")
public class MappingConfiguration implements Serializable {

    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }

    @Bean
    public RetailSellerMapper retailSellerMapper() { return new RetailSellerMapper(); }

    @Bean
    public RetailSellerOrderMapper retailSellerOrderMapper() {
        return new RetailSellerOrderMapper();
    }
    @Bean
    public WholesalerMapper wholesalerMapper() {
        return new WholesalerMapper();
    }
    @Bean
    public WholesalerOrderMapper wholesalerOrderMapper() { return new WholesalerOrderMapper(); }


}
