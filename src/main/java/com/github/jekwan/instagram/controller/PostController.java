package com.github.jekwan.instagram.controller;

import com.github.jekwan.instagram.dto.*;
import com.github.jekwan.instagram.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostResponseDto postResponseDto = postService.createPost(postCreateRequestDto);
        ApiResponse<PostResponseDto> response = new ApiResponse<>(200, postResponseDto, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> getPostById(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.getPost(postId);
        ApiResponse<PostResponseDto> response = new ApiResponse<>(200, postResponseDto, null);
        return ResponseEntity.ok(response);
    }

}
