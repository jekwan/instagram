package com.github.jekwan.instagram.controller;

import com.github.jekwan.instagram.dto.ApiResponse;
import com.github.jekwan.instagram.dto.PostResponseDto;
import com.github.jekwan.instagram.service.NewsfeedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/newsfeed")
public class NewsfeedController {

    private final NewsfeedService newsfeedService;

    public NewsfeedController(NewsfeedService newsfeedService) {
        this.newsfeedService = newsfeedService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getNewsfeed(@PathVariable Long id) {
        List<PostResponseDto> posts = newsfeedService.getNewsfeed(id);
        ApiResponse<List<PostResponseDto>> response = new ApiResponse<>(200, posts, null);
        return ResponseEntity.ok(response);
    }
}
