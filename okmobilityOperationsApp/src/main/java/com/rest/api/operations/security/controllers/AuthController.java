package com.rest.api.operations.security.controllers;

import com.rest.api.operations.services.bussneslogic.implementation.OperationServiceImpl;
import com.rest.api.operations.services.models.dtos.LoginDTO;
import com.rest.api.operations.persistence.entities.UserEntity;
import com.rest.api.operations.security.services.IAuthService;
import com.rest.api.operations.services.models.dtos.ResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationServiceImpl.class);

    @PostMapping("/register")
    private ResponseEntity<ResponseDTO> addUser(@RequestBody UserEntity user) throws Exception {
        return new ResponseEntity<>(authService.register(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    private ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO loginRequest) throws Exception {
    	

    	
        HashMap<String, String> login = authService.login(loginRequest);
        
        LOGGER.info("Rest Auth  -  request for loggin in progress ");
        
        if (login.containsKey("jwt")) {
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.ACCEPTED);
            
      
        } else {
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.UNAUTHORIZED);
        }
  
    }
}
