package dev.cfan.blogapi.service;

import dev.cfan.blogapi.domain.User;
import dev.cfan.blogapi.exception.InformationExistException;
import dev.cfan.blogapi.repository.UserRepository;
import dev.cfan.blogapi.security.JWTUtils;
import dev.cfan.blogapi.security.auth.AuthenticationRequest;
import dev.cfan.blogapi.security.auth.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByName(String username) {
        return userRepository.findByName(username);
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }
}
