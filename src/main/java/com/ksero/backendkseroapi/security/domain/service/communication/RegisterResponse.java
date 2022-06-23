package com.ksero.backendkseroapi.security.domain.service.communication;

import com.ksero.backendkseroapi.security.resource.UserResource;
import com.ksero.backendkseroapi.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {

    public RegisterResponse(String message){
        super(message);
    }

    public RegisterResponse(UserResource resource){
        super(resource);
    }
}