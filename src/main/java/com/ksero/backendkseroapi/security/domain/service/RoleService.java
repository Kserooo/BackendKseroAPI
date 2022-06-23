package com.ksero.backendkseroapi.security.domain.service;

import com.ksero.backendkseroapi.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {

    void seed();

    List<Role> getAll();
}