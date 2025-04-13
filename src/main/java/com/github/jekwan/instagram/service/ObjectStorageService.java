package com.github.jekwan.instagram.service;

import com.github.jekwan.instagram.dto.SignedUrlResponseDto;

public interface ObjectStorageService {
    SignedUrlResponseDto generateSignedUploadUrl(String objectName, String contentType);
    SignedUrlResponseDto generateSignedDownloadUrl(String objectName);
    void deleteObject(String objectName);
}
