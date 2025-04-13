package com.github.jekwan.instagram.service;

import com.github.jekwan.instagram.dto.SignedUrlResponseDto;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
public class ObjectStorageServiceGcs implements ObjectStorageService {
    private final Storage storage;

    @Value("${gcp.bucket-name}")
    private String bucketName;

    public ObjectStorageServiceGcs(Storage storage) {
        this.storage = storage;
    }

    @Override
    public SignedUrlResponseDto generateSignedUploadUrl(String objectName, String contentType) {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, objectName).setContentType(contentType).build();

        URL uploadUrl = storage.signUrl(blobInfo, 5, TimeUnit.MINUTES,
                Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
                Storage.SignUrlOption.withV4Signature()
        );
        return new SignedUrlResponseDto(uploadUrl.toString(), objectName);
    }

    @Override
    public SignedUrlResponseDto generateSignedDownloadUrl(String objectName) {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, objectName).build();

        URL downloadUrl = storage.signUrl(blobInfo, 5, TimeUnit.MINUTES,
                Storage.SignUrlOption.httpMethod(HttpMethod.GET),
                Storage.SignUrlOption.withV4Signature()
        );
        return new SignedUrlResponseDto(downloadUrl.toString(), objectName);
    }

    @Override
    public void deleteObject(String objectName) {
        storage.delete(bucketName, objectName);
    }
}
