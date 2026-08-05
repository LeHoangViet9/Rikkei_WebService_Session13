package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Tắt CSRF vì ứng dụng là REST API
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Cấu hình STATELESS - Không tạo Session / JSESSIONID trên Server
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 3. Phân quyền Endpoint
                .authorizeHttpRequests(auth -> auth
                        // Cho phép truy cập công khai các API đăng ký, đăng nhập và test-token
                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login",
                                "/api/auth/test-token",
                                "/api/products/**"
                        ).permitAll()
                        // Mọi request còn lại yêu cầu phải xác thực
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}