package com.example;

import com.example.entity.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppllicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@example.com");
        System.out.println(user);
    }
}
