package dev.cfan.blogapi;

import static org.assertj.core.api.Assertions.assertThat;

// Borrowed from https://spring.io/guides/gs/testing-web/

import dev.cfan.blogapi.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private UserController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}