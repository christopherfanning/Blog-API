package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user/")
    public User getAllUsers(){
        userService.getAllUsers();
    }

}
