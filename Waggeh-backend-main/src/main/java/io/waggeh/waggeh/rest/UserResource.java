package io.waggeh.waggeh.rest;
import io.waggeh.waggeh.model.UserDTO;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserResource {

    private final UserService userService;

    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseWrapper<List<UserDTO>>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/info")
    public ResponseEntity<ApiResponseWrapper<UserDTO>> getUserInfo() {
        return ResponseEntity.ok(userService.findCurrentUserInfo());
    }
    @PostMapping  ("/update")
    public ResponseEntity<ApiResponseWrapper<UserDTO>> updateCurrentUserInfo(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUserInfo(userDTO)) ;
    }


}
