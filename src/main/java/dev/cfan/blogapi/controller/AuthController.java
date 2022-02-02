package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.User;
import dev.cfan.blogapi.security.auth.AuthenticationRequest;
import dev.cfan.blogapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return authService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println("calling loginUser ==>");
        return authService.loginUser(authenticationRequest);
    }
}
