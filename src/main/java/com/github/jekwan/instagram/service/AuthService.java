package com.github.jekwan.instagram.service;

import com.github.jekwan.instagram.dto.UserLoginDto;
import com.github.jekwan.instagram.dto.UserRegistrationDto;
import com.github.jekwan.instagram.dto.UserResponseDto;
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

    public UserResponseDto registerUser(UserRegistrationDto userRegistrationDto) {
        Optional<User> existingUser = userRepository.findByEmail(userRegistrationDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        String hashedPassword = BCrypt.hashpw(userRegistrationDto.getPassword(), BCrypt.gensalt());
        User user = new User(userRegistrationDto.getName(), userRegistrationDto.getEmail(), hashedPassword);
        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );
    }

    public UserResponseDto loginUser(UserLoginDto userLoginDto) {
        Optional<User> existingUser = userRepository.findByEmail(userLoginDto.getEmail());
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = existingUser.get();
        if (BCrypt.checkpw(userLoginDto.getPassword(), user.getPasswordHash())) {
            return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
        } else {
            throw new RuntimeException("Incorrect password");
        }
    }
}
