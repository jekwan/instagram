package com.github.jekwan.instagram.dto;

public class SignedUrlResponseDto {
    private String url;
    private String objectName;

    public SignedUrlResponseDto(String url, String objectName) {
        this.url = url;
        this.objectName = objectName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
