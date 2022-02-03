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

    @GetMapping("/hello")
    public String helloUser(){
        return "Hello User!";
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        System.out.println("Hitting the getAllUsers endpoint. ");
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUserByName(@PathVariable(value = "username") String username){
        return userService.getUserByName(username);
    }
}
