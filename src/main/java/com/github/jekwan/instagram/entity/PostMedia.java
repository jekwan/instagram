package com.github.jekwan.instagram.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "post_media")
public class PostMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "media_type", nullable = false, length = 10)
    private String mediaType;

    @Column(name = "media_url", nullable = false, columnDefinition = "TEXST")
    private String mediaUrl;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder = 0;

    public PostMedia() {
    }

    public PostMedia(Post post, String mediaType, String mediaUrl, int sortOrder) {
        this.post = post;
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
        this.sortOrder = sortOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
