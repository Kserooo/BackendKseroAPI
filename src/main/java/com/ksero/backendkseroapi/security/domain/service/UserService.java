package com.ksero.backendkseroapi.security.domain.service;

import com.ksero.backendkseroapi.security.domain.model.entity.User;
import com.ksero.backendkseroapi.security.domain.service.communication.AuthenticateRequest;
import com.ksero.backendkseroapi.security.domain.service.communication.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService extends UserDetailsService {

    ResponseEntity<?> authenticate(AuthenticateRequest request);

    ResponseEntity<?>  register(RegisterRequest request);

    List<User> getAll();
}