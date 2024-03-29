package com.ksero.backendkseroapi.security.service;

import com.ksero.backendkseroapi.security.domain.model.entity.Role;
import com.ksero.backendkseroapi.security.domain.model.entity.User;
import com.ksero.backendkseroapi.security.domain.model.enumeration.Roles;
import com.ksero.backendkseroapi.security.domain.persistence.RoleRepository;
import com.ksero.backendkseroapi.security.domain.persistence.UserRepository;
import com.ksero.backendkseroapi.security.domain.service.UserService;
import com.ksero.backendkseroapi.security.domain.service.communication.AuthenticateRequest;
import com.ksero.backendkseroapi.security.domain.service.communication.AuthenticateResponse;
import com.ksero.backendkseroapi.security.domain.service.communication.RegisterRequest;
import com.ksero.backendkseroapi.security.domain.service.communication.RegisterResponse;
import com.ksero.backendkseroapi.security.middleware.UserDetailsImpl;
import com.ksero.backendkseroapi.security.resource.AuthenticateResource;
import com.ksero.backendkseroapi.security.resource.UserResource;
import com.ksero.backendkseroapi.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(
            UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    EnhancedModelMapper mapper;

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl)  authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateResource resource = mapper.map(userDetails, AuthenticateResource.class);
            resource.setRoles(roles);

            AuthenticateResponse response = new AuthenticateResponse(resource);
            return ResponseEntity.ok(response.getResource());

        }catch (Exception e) {
            AuthenticateResponse response = new AuthenticateResponse(String.format(
                    "An error ocurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());

        }
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            AuthenticateResponse response = new AuthenticateResponse("Username is already used");
            return ResponseEntity.badRequest().body(response.getMessage());
        }

        if (userRepository.existsByEmail(request.getEmail())){
            AuthenticateResponse response = new AuthenticateResponse("Email is already used");
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        try{
            Set<String> roleStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if(roleStringSet == null){
                roleRepository.findByName(Roles.ROLE_USER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Roles not found"));
            }else{
                roleStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Roles not found")));
            }
            logger.info("Roles: {}", roles);

            User user = new User().withUsername(request.getUsername())
                    .withEmail(request.getEmail()).withPassword(encoder.encode(request.getPassword()))
                    .withRoles(roles);
            userRepository.save(user);

            UserResource resource = mapper.map(user, UserResource.class);
            RegisterResponse response = new RegisterResponse(resource);
            return ResponseEntity.ok(response.getResource());

        }catch (Exception e){
            RegisterResponse response = new RegisterResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());

        }

    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format(
                        "User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }
}
