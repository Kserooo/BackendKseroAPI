package com.ksero.backendkseroapi.security.domain.service.communication;

public class RegisterResponse extends BaseResponse<UserResource> {

    public RegisterResponse(String message){
        super(message);
    }

    public RegisterResponse(UserResource resource){
        super(resource);
    }
}