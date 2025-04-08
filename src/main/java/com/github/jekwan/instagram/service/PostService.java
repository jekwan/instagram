package com.github.jekwan.instagram.service;

import com.github.jekwan.instagram.dto.PostCreateRequestDto;
import com.github.jekwan.instagram.dto.PostMediaResponseDto;
import com.github.jekwan.instagram.dto.PostResponseDto;
import com.github.jekwan.instagram.entity.Post;
import com.github.jekwan.instagram.entity.PostMedia;
import com.github.jekwan.instagram.entity.User;
import com.github.jekwan.instagram.exception.ResourceNotFoundException;
import com.github.jekwan.instagram.repository.PostMediaRepository;
import com.github.jekwan.instagram.repository.PostRepository;
import com.github.jekwan.instagram.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMediaRepository postMediaRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, PostMediaRepository postMediaRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMediaRepository = postMediaRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PostResponseDto createPost(PostCreateRequestDto postCreateRequestDto) {
        User user = userRepository
                .findById(postCreateRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = new Post(postCreateRequestDto.getTitle(), postCreateRequestDto.getContents());
        post.setUser(user);
        Post savedPost = postRepository.save(post);

        List<PostMedia> media = postCreateRequestDto
                .getMedia()
                .stream()
                .map(mediaDto -> new PostMedia(savedPost, mediaDto.getMediaType(), mediaDto.getMediaUrl(), mediaDto.getSortOrder()))
                .collect(Collectors.toList());
        List<PostMedia> savedMedia =  postMediaRepository.saveAll(media);

        return new PostResponseDto(
                savedPost.getUser().getName(),
                savedPost.getTitle(),
                savedPost.getContents(),
                savedPost.getCreatedAt(),
                savedPost.getUpdatedAt(),
                savedMedia.stream()
                        .map(m -> new PostMediaResponseDto(
                                m.getMediaType(),
                                m.getMediaUrl(),
                                m.getSortOrder())).collect(Collectors.toUnmodifiableList())
        );
    }

    public Post updatePost(Long id, Post updatedPost) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        post.setTitle(updatedPost.getTitle());
        post.setContents(updatedPost.getContents());

        return postRepository.save(post);
    }

    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        List<PostMediaResponseDto> media = post.getMediaList()
                .stream()
                .map(m -> new PostMediaResponseDto(m.getMediaType(), m.getMediaUrl(), m.getSortOrder())).collect(Collectors.toList());

        return new PostResponseDto(post.getUser().getName(), post.getTitle(), post.getContents(), post.getCreatedAt(), post.getUpdatedAt(), media);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
