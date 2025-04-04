package com.github.jekwan.instagram.controller;

import com.github.jekwan.instagram.dto.ApiResponse;
import com.github.jekwan.instagram.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/{followerId}/{followeeId}")
    public ResponseEntity<ApiResponse<Boolean>> follow(
            @PathVariable Long followerId,
            @PathVariable Long followeeId) {
        Boolean result = followService.follow(followerId, followeeId);
        ApiResponse<Boolean> response = new ApiResponse<>(200, result, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{followerId}/{followeeId}")
    public ResponseEntity<ApiResponse<Boolean>> unfollow(
            @PathVariable Long followerId,
            @PathVariable Long followeeId) {
        Boolean result = followService.unfollow(followerId, followeeId);
        ApiResponse<Boolean> response = new ApiResponse<>(200, result, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
