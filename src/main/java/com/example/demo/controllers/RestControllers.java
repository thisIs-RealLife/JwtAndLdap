package com.example.demo.controllers;

import com.example.demo.security.LoginFrom;
import com.example.demo.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestControllers {
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    public RestControllers(AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Security";
    }

    @PostMapping("/login")
    public ResponseEntity<?> registration(@RequestBody LoginFrom loginFrom){

        return ResponseEntity.ok("Ok");
    }

}
