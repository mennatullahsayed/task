package io.waggeh.waggeh.mappers;

import io.waggeh.waggeh.model.UserDTO;
import io.waggeh.waggeh.model.requests.AuthenticationRequest;
import io.waggeh.waggeh.model.requests.LoginRequest;
import io.waggeh.waggeh.model.requests.SignUpRequest;

public class AuthenticationRequestMapper {
    public static SignUpRequest toUserDTO(AuthenticationRequest authenticationRequest) {
        SignUpRequest SignUpRequest = new SignUpRequest();
        SignUpRequest.setEmail(authenticationRequest.getEmail());
        SignUpRequest.setFirstName(authenticationRequest.getFirstName());
        SignUpRequest.setPassword(authenticationRequest.getEmail()+"@waggeh");
        SignUpRequest.setRole(authenticationRequest.getRole());
        SignUpRequest.setPhotoUrl(authenticationRequest.getPhotoUrl());
        return SignUpRequest;
    }
    public static LoginRequest toLoginRequest(AuthenticationRequest authenticationRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(authenticationRequest.getEmail());
        loginRequest.setPassword(authenticationRequest.getEmail()+"@waggeh");
        return loginRequest;
    }
}
