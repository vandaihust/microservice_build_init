package com.vandai.Auth.service.impl;

import com.vandai.Auth.dto.UserDto;
import com.vandai.Auth.dto.request.LoginRequest;
import com.vandai.Auth.dto.request.SignupRequest;
import com.vandai.Auth.dto.response.JwtResponse;
import com.vandai.Auth.repository.RoleRepository;
import com.vandai.Auth.repository.UserRepository;
import com.vandai.Auth.security.jwt.JwtUtils;
import com.vandai.Auth.security.services.UserDetailsImpl;
import com.vandai.Auth.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .username(userDetails.getUsername())
                .roles(roles)
                .token(jwt)
                .build();
    }

    @Override
    public UserDto signUp(SignupRequest signupRequest) {
        return null;
    }
}
