package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UserRegisterRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.UserResponse;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
    String generateTokenForUser(String username);
    LoginResponse login(LoginRequest request);
}
