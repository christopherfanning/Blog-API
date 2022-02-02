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
public class AuthService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    private JpaUserService jpaUserService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User userObject) {
        System.out.println("service calling createUser ==>");
        if (!userRepository.existsByEmail(userObject.getEmail())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email address " + userObject.getEmail() +
                    " already exists");
        }
    }

    public ResponseEntity<?> loginUser(AuthenticationRequest authenticationRequest) {
        System.out.println("service calling loginUser ==>");
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        final UserDetails userDetails = jpaUserService.loadUserByUsername(authenticationRequest.getUsername());
        final String JWT = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(JWT));
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
