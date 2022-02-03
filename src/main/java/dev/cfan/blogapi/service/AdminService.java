package dev.cfan.blogapi.service;

import dev.cfan.blogapi.domain.Role;
import dev.cfan.blogapi.domain.User;
import dev.cfan.blogapi.exception.NotFoundException;
import dev.cfan.blogapi.repository.RoleRepository;
import dev.cfan.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public User addUserToRole(Long userId, String roleType){

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()){
            throw new NotFoundException("No user with id " + userId + "is found");
        }
        Role role = new Role();
        role.setRole(roleType);
        role.setUser(user.get());


        user.get().setUserRole(role);

        roleRepository.save(role);

        userRepository.save(user.get());
        return user.get();
    }
}
