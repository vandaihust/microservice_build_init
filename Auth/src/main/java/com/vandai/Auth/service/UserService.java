package com.vandai.Auth.service;

import com.vandai.Auth.dto.UserDto;
import com.vandai.Auth.dto.request.LoginRequest;
import com.vandai.Auth.dto.request.SignupRequest;
import com.vandai.Auth.dto.response.JwtResponse;

public interface UserService {
    JwtResponse login(LoginRequest loginRequest);

    UserDto signUp(SignupRequest signupRequest);
}
