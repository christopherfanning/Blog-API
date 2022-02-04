package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.User;
import dev.cfan.blogapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addrole/{userId}/{role}")
    public User addUserToRole(@PathVariable(value = "userId") Long userId,
                              @PathVariable(value = "role"  ) String role){
        return adminService.addUserToRole(userId, role);
    }

    @GetMapping("/hello")
        public String adminHello(){
        return "Hello Admin. How may I serve?";

    }
    // TODO delete mappings for everything.

}
