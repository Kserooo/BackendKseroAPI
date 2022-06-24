package com.ksero.backendkseroapi.security.mapping;

import com.ksero.backendkseroapi.ksero.domain.model.entity.Product;
import com.ksero.backendkseroapi.ksero.resources.product.ProductResource;
import com.ksero.backendkseroapi.security.domain.model.entity.Role;
import com.ksero.backendkseroapi.security.domain.model.entity.User;
import com.ksero.backendkseroapi.security.resource.UserResource;
import com.ksero.backendkseroapi.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    Converter<Role, String> roleToString = new AbstractConverter<Role, String>(){
        @Override
        protected  String convert(Role role){
            return  role == null ? null : role.getName().name();
        }
    };

    //Object Mapping

    public UserResource toResource(User model){
        mapper.addConverter(roleToString);
        return mapper.map(model, UserResource.class);
    }

    public List<UserResource> toResource(List<User> model){
        return mapper.mapList(model, UserResource.class);
    }

}
