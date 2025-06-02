package com.jaeholee.devhub.controller;

import com.jaeholee.devhub.dto.LoginDto;
import com.jaeholee.devhub.dto.SignupDto;
import com.jaeholee.devhub.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.AuthenticationException;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final String token = "oracle_4U";

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(LoginDto loginDto, HttpServletRequest request) {
        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // SecurityContext 생성 및 설정
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            // 세션에 SecurityContext 저장
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

            Map<String, String> map = new HashMap<>();
            map.put("message", "로그인 성공");
            return ResponseEntity.ok(map);
        } catch (Exception e){
            log.error(e);
            Map<String, String> map = new HashMap<>();
            map.put("message", "로그인 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<Map<String, String>> signup(SignupDto signupDto) {
        Map<String, String> map = new HashMap<>();
        if (signupDto.getToken().equals(token)) {
            userService.registerUser(signupDto.getUsername(), signupDto.getPassword());
            map.put("status", "ok");
            return ResponseEntity.ok(map);  // 200 OK
        } else {
            map.put("status", "error");
            map.put("message", "토큰이 정확하지 않습니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);  // 400 Bad Request
        }
    }
}
