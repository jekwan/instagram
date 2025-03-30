package com.github.jekwan.instagram.controller;

import com.github.jekwan.instagram.dto.UserLoginDto;
import com.github.jekwan.instagram.dto.UserRegistrationDto;
import com.github.jekwan.instagram.dto.UserResponseDto;
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
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRegistrationDto userRegistrationDto) {
        UserResponseDto userResponseDto = authService.registerUser(userRegistrationDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginDto userLoginDto, HttpSession session) {
        UserResponseDto userResponseDto = authService.loginUser(userLoginDto);
        session.setAttribute("user", userResponseDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("logout");
    }
}
