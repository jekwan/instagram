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
    public ResponseEntity<ApiResponse<UserRegisterResponseDto>> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        UserRegisterResponseDto userRegisterResponseDto = authService.registerUser(userRegisterRequestDto);

        ApiResponse<UserRegisterResponseDto> response = new ApiResponse<>(200, userRegisterResponseDto, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponseDto>> login(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpSession session) {
        UserLoginResponseDto userLoginResponseDto = authService.loginUser(userLoginRequestDto);
        session.setAttribute("user", userLoginResponseDto);

        ApiResponse<UserLoginResponseDto> response = new ApiResponse<>(200, userLoginResponseDto, null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
        session.invalidate();

        ApiResponse<String> response = new ApiResponse<>(200, "logout", null);
        return ResponseEntity.ok(response);
    }
}
