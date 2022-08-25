package com.pvbank.portal.controller;

import com.pvbank.portal.request.UserLoginRequest;
import com.pvbank.portal.request.UserRegisterRequest;
import com.pvbank.portal.response.UserLoginResponse;
import com.pvbank.portal.response.UserRegisterResponse;
import com.pvbank.portal.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @CrossOrigin
    @PostMapping(value = "/user-register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRegisterResponse> saveUser(@Validated @RequestBody UserRegisterRequest userRequest) {
        UserRegisterResponse response = userService.saveUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping(value = "/user-login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginResponse> verifyUser(@Validated @RequestBody UserLoginRequest userRequest) {
        UserLoginResponse response = userService.verifyUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }






}
