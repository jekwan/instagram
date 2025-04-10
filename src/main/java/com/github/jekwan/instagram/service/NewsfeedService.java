package com.github.jekwan.instagram.service;

import com.github.jekwan.instagram.dto.PostMediaResponseDto;
import com.github.jekwan.instagram.dto.PostResponseDto;
import com.github.jekwan.instagram.entity.Post;
import com.github.jekwan.instagram.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsfeedService {

    private final PostRepository postRepository;

    public NewsfeedService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostResponseDto> getNewsfeed(Long userId) {
        List<Post> posts = postRepository.findNewsFeedByFollowerId(userId);

        return posts
                .stream()
                .map(post -> new PostResponseDto(
                        post.getUser().getName(),
                        post.getTitle(),
                        post.getContents(),
                        post.getCreatedAt(),
                        post.getUpdatedAt(),
                        post.getMediaList()
                                .stream()
                                .map(m -> new PostMediaResponseDto(m.getMediaType(), m.getMediaUrl(), m.getSortOrder())).collect(Collectors.toList()))
                ).toList();

    }
}
