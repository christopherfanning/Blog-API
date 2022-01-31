package dev.cfan.blogapi;

import dev.cfan.blogapi.controller.UserController;
import dev.cfan.blogapi.domain.User;
import dev.cfan.blogapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;

@SpringBootTest
@ActiveProfiles("test")
public class UserIntegrationTest {

    @Autowired
    private UserController controller;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void createUser(){

        User user = new User("Chris", "Chris@chris.com");

        given(userRepository.save(user));
        Long userId = user.getId();

        // then
        System.out.println(userRepository.findById(userId));
        assertThat(userRepository.findById(userId)).isNotEmpty();

        userRepository.deleteAll();

    }
}
