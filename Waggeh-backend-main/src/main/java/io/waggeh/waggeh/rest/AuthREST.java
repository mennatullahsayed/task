package io.waggeh.waggeh.rest;






import io.waggeh.waggeh.domain.User;
import io.waggeh.waggeh.mappers.AuthenticationRequestMapper;
import io.waggeh.waggeh.mappers.UserMapper;
import io.waggeh.waggeh.model.UserDTO;
import io.waggeh.waggeh.model.requests.AuthenticationRequest;
import io.waggeh.waggeh.model.requests.LoginRequest;
import io.waggeh.waggeh.model.requests.SignUpRequest;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;

import io.waggeh.waggeh.model.responses.UserDTOResponse;
import io.waggeh.waggeh.service.UserSecurityService;

import io.waggeh.waggeh.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthREST {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserSecurityService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseWrapper<UserDTOResponse>> registerUser(@RequestBody SignUpRequest signUpRequest) {

        ApiResponseWrapper<UserDTOResponse> userApiResponseWrapper = userService.signUp(signUpRequest,passwordEncoder);
        return ResponseEntity.ok(userApiResponseWrapper);
    }

    @GetMapping("/checkUser")
    public ResponseEntity<ApiResponseWrapper<String>> checkUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok(new ApiResponseWrapper<>(true,"success",currentPrincipalName));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponseWrapper<String>> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new ApiResponseWrapper<String>(true,"logged in successfully",jwt));

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseWrapper<Map<String,Object>>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        loginRequest.setEmail(loginRequest.getEmail().toLowerCase());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);
        Map<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        map.put("user", UserMapper.toDTO((User) authentication.getPrincipal()));



        return ResponseEntity.ok(new ApiResponseWrapper<Map<String,Object>>(true,"logged in successfully",map));
    }

    @PostMapping("/google/authenticate")
    public ResponseEntity<ApiResponseWrapper<Map<String,Object>>> googleAuthenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationRequest.setEmail(authenticationRequest.getEmail().toLowerCase());
        if(!userService.existsByEmail(authenticationRequest.getEmail())) {
            SignUpRequest signUpRequest = AuthenticationRequestMapper.toUserDTO(authenticationRequest);
            ApiResponseWrapper<UserDTOResponse> signedUpUser = userService.signUp(signUpRequest, passwordEncoder);
            if (!signedUpUser.getSuccess()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseWrapper<Map<String,Object>>(false, "failed to sign up user"));
            }
        }
        LoginRequest signUpRequest = AuthenticationRequestMapper.toLoginRequest(authenticationRequest);
        return authenticateUser(signUpRequest);
    }

}
