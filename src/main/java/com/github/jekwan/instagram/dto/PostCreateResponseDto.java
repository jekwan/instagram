package com.github.jekwan.instagram.dto;

public class PostCreateResponseDto {
    private Long id;

    public PostCreateResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
