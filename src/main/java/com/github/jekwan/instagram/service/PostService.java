package com.github.jekwan.instagram.service;

import com.github.jekwan.instagram.dto.PostCreateRequestDto;
import com.github.jekwan.instagram.dto.PostMediaResponseDto;
import com.github.jekwan.instagram.dto.PostResponseDto;
import com.github.jekwan.instagram.entity.Post;
import com.github.jekwan.instagram.entity.PostMedia;
import com.github.jekwan.instagram.exception.ResourceNotFoundException;
import com.github.jekwan.instagram.repository.PostMediaRepository;
import com.github.jekwan.instagram.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMediaRepository postMediaRepository;

    public PostService(PostRepository postRepository, PostMediaRepository postMediaRepository) {
        this.postRepository = postRepository;
        this.postMediaRepository = postMediaRepository;
    }

    @Transactional
    public Long createPost(PostCreateRequestDto postCreateRequestDto) {
        Post createdPost = postRepository.save(new Post(postCreateRequestDto.getTitle(), postCreateRequestDto.getContents()));

        List<PostMedia> media = postCreateRequestDto
                .getMedia()
                .stream()
                .map(mediaDto -> new PostMedia(createdPost, mediaDto.getMediaType(), mediaDto.getMediaUrl(), mediaDto.getSortOrder()))
                .collect(Collectors.toList());

        postMediaRepository.saveAll(media);
        return createdPost.getId();
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

        return new PostResponseDto(post.getUser().getId(), post.getTitle(), post.getContents(), post.getCreatedAt(), post.getUpdatedAt(), media);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
