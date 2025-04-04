package com.github.jekwan.instagram.dto;

public class PostMediaCreateRequestDto {
    private String mediaType;
    private String mediaUrl;
    private int sortOrder;

    public PostMediaCreateRequestDto() {}
    public PostMediaCreateRequestDto(String mediaType, String mediaUrl, int sortOrder) {
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
        this.sortOrder = sortOrder;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
