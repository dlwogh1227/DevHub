package com.jaeholee.devhub.service;

import com.jaeholee.devhub.domain.User;
import com.jaeholee.devhub.dto.SignupDto;
import com.jaeholee.devhub.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public void registerUser(SignupDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER"); // 기본 권한
        userMapper.insertUser(user);
    }
}
