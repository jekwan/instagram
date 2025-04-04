package com.github.jekwan.instagram.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class PostResponseDto {
    private Long userId;
    private String title;
    private String contents;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private List<PostMediaResponseDto> media;

    public PostResponseDto(Long userId, String title, String contents, OffsetDateTime createdAt, OffsetDateTime updatedAt, List<PostMediaResponseDto> media) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.media = media;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<PostMediaResponseDto> getMedia() {
        return media;
    }

    public void setMedia(List<PostMediaResponseDto> media) {
        this.media = media;
    }
}
