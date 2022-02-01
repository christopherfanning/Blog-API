package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.User;
import dev.cfan.blogapi.security.auth.AuthenticationRequest;
import dev.cfan.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println("calling loginUser ==>");
        return userService.loginUser(authenticationRequest);
    }
}
