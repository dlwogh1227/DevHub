package com.jaeholee.devhub.config;

import com.jaeholee.devhub.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // csrf 설정도 람다 스타일로
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login", "/signup", "/post_files/**", "/gallery/list", "/gallery/read", "/error").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginProcessingUrl("/login") // 모달에서 POST 요청 보낼 URL
                    .defaultSuccessUrl("/gallery/list", false) // 로그인 성공 시 리다이렉트
                    .failureUrl("/?error=true") // 로그인 실패 시 리다이렉트 (모달에서 에러 처리용)
                    .permitAll() // 로그인 처리 URL에 대한 접근 허용
            )
            .logout(logout -> logout
                    .logoutSuccessUrl("/gallery/list")
                    .permitAll()
            )
            .exceptionHandling(ex -> ex
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write("로그인이 필요합니다");
                    })
            );

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
