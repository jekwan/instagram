package com.github.jekwan.instagram.controller;

import com.github.jekwan.instagram.dto.*;
import com.github.jekwan.instagram.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        UserResponseDto userResponseDto = authService.registerUser(userRegisterRequestDto);

        ApiResponse<UserResponseDto> response = new ApiResponse<>(200, userResponseDto, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> login(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpSession session) {
        UserResponseDto userResponseDto = authService.loginUser(userLoginRequestDto);
        session.setAttribute("user", userResponseDto);

        ApiResponse<UserResponseDto> response = new ApiResponse<>(200, userResponseDto, null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
        session.invalidate();

        ApiResponse<String> response = new ApiResponse<>(200, "logout", null);
        return ResponseEntity.ok(response);
    }
}
