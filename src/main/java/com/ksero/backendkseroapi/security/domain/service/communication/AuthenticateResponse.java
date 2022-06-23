package com.ksero.backendkseroapi.security.domain.service.communication;

import com.ksero.backendkseroapi.security.resource.AuthenticateResource;
import com.ksero.backendkseroapi.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {

    public AuthenticateResponse(String message){
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource){
        super(resource);
    }
}