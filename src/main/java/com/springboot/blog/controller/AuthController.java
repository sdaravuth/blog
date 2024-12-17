package com.springboot.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.JWTAuthentication;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.service.AuthService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //build login rest api
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthentication> login(@RequestBody LoginDto login) {
        String token = authService.login(login);
        JWTAuthentication jwtAuthResponse = new JWTAuthentication();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse , HttpStatus.OK);
    }

     //build login rest api
     @PostMapping("/register")
     public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
         return new ResponseEntity<>(authService.register(registerDto), HttpStatus.CREATED);
     }
    

    
}
