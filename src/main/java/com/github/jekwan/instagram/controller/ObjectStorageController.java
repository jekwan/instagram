package com.github.jekwan.instagram.controller;


import com.github.jekwan.instagram.dto.ApiResponse;
import com.github.jekwan.instagram.dto.SignedUrlResponseDto;
import com.github.jekwan.instagram.service.ObjectStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/storage")
public class ObjectStorageController {
    private final ObjectStorageService objectStorageService;

    public ObjectStorageController(ObjectStorageService objectStorageService) {
        this.objectStorageService = objectStorageService;
    }

    @GetMapping("/upload-url")
    public ResponseEntity<ApiResponse<SignedUrlResponseDto>> generateSignedUploadUrl(
            @RequestParam String filename,
            @RequestParam(required = false, defaultValue = "image/jpeg") String contentType
    ) {
        String objectName = UUID.randomUUID() + "_" + filename;

        SignedUrlResponseDto signedUrlResponseDto = objectStorageService.generateSignedUploadUrl(objectName, contentType);
        ApiResponse<SignedUrlResponseDto> response = new ApiResponse<>(200, signedUrlResponseDto, null);
        return ResponseEntity.ok(response);
    }
}
