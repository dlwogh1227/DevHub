package com.jaeholee.devhub.service;

import com.jaeholee.devhub.dto.SignupDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void testInsertUser() {
        SignupDto signupDto = new SignupDto();
        signupDto.setUsername("test");
        signupDto.setPassword("test");
        userService.registerUser(signupDto);
    }

}
