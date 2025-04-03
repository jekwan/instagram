package com.github.jekwan.instagram.service;

import com.github.jekwan.instagram.dto.*;
import com.github.jekwan.instagram.entity.User;
import com.github.jekwan.instagram.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(userRegisterRequestDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        String hashedPassword = BCrypt.hashpw(userRegisterRequestDto.getPassword(), BCrypt.gensalt());
        User user = new User(userRegisterRequestDto.getName(), userRegisterRequestDto.getEmail(), hashedPassword);
        User savedUser = userRepository.save(user);

        return new UserRegisterResponseDto(
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    public UserLoginResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(userLoginRequestDto.getEmail());
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = existingUser.get();
        if (BCrypt.checkpw(userLoginRequestDto.getPassword(), user.getPasswordHash())) {
            return new UserLoginResponseDto(
                    user.getName(),
                    user.getEmail()
            );
        } else {
            throw new RuntimeException("Incorrect password");
        }
    }
}
