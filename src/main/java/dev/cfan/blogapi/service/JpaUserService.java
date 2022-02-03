package dev.cfan.blogapi.service;

// Stolen From : https://git.generalassemb.ly/sureshmelvinsigera/Java-Spring/blob/master/README.md

import dev.cfan.blogapi.domain.JpaUser;
import dev.cfan.blogapi.domain.User;
import dev.cfan.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JpaUserService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findUserByName(name);
        return new JpaUser(user);
    }
}
