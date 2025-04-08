package com.github.jekwan.instagram.service;

import com.github.jekwan.instagram.dto.UserResponseDto;
import com.github.jekwan.instagram.entity.User;
import com.github.jekwan.instagram.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDto(user.getName(), user.getEmail()))
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<UserResponseDto> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return Optional.of(new UserResponseDto(user.get().getName(), user.get().getEmail()));
        }

        return Optional.empty();
    }

    public UserResponseDto saveUser(User user) {
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getName(), savedUser.getEmail());
    }

    public Optional<UserResponseDto> updateUser(Long id, User newUserData) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User savedUser = userRepository.save(newUserData);
            return Optional.of(new UserResponseDto(savedUser.getName(), savedUser.getEmail()));
        }

        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
