package com.github.jekwan.instagram.dto;

import java.util.List;

public class PostCreateRequestDto {
    private String title;
    private String contents;
    private List<PostMediaCreateRequestDto> media;

    public PostCreateRequestDto() {}

    public PostCreateRequestDto(String title, String contents, List<PostMediaCreateRequestDto> media) {
        this.title = title;
        this.contents = contents;
        this.media = media;
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

    public List<PostMediaCreateRequestDto> getMedia() {
        return media;
    }

    public void setMedia(List<PostMediaCreateRequestDto> media) {
        this.media = media;
    }
}
