package com.example.blogpr.service.impl;

import com.example.blogpr.dto.LoginDto;
import com.example.blogpr.dto.RegisterDto;
import com.example.blogpr.entity.Role;
import com.example.blogpr.entity.User;
import com.example.blogpr.repository.RoleRepository;
import com.example.blogpr.repository.UserRepository;
import com.example.blogpr.security.JwtTokenProvider;
import com.example.blogpr.service.AuthService;
import io.jsonwebtoken.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByUsernameOrEmail(registerDto.getUsername(), registerDto.getEmail())){
            throw new RuntimeException("User already exists. Please choose a new username");
        }

        User user = new User();

        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());

        Set<Role> roleSet = roleRepository.getRoleByRoleName("ROLE_USER");
        user.setRoles(roleSet);

        userRepository.save(user);

        return "User Successfully registered";
    }
}
