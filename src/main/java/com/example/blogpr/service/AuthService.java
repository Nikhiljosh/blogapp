package com.example.blogpr.service;

import com.example.blogpr.dto.LoginDto;
import com.example.blogpr.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
