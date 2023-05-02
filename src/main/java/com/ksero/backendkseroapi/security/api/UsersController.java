package com.ksero.backendkseroapi.security.api;

import com.ksero.backendkseroapi.security.domain.service.UserService;
import com.ksero.backendkseroapi.security.domain.service.communication.AuthenticateRequest;
import com.ksero.backendkseroapi.security.domain.service.communication.RegisterRequest;
import com.ksero.backendkseroapi.security.mapping.UserMapper;
import com.ksero.backendkseroapi.security.resource.UserResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SecurityRequirement(name = "acme")
@Tag(name="Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;
    private final UserMapper mapper;

    public UsersController(UserService userService, UserMapper mapper){
        this.mapper = mapper;
        this.userService = userService;
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody
                                              AuthenticateRequest request){
        return userService.authenticate(request);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody
                                          RegisterRequest request){
        return userService.register(request);
    }

    @GetMapping("/auth/get-all")
    public List<UserResource> getAllUsers(){
        return mapper.toResource(userService.getAll());
    }

    @GetMapping("/auth/verify-token-retail-seller")
    public boolean verifyTokenRetailSeller(){
        return true;
    }

    @GetMapping("/auth/verify-token-wholesaler")
    public boolean verifyTokenWholesaler(){
        return true;
    }

}