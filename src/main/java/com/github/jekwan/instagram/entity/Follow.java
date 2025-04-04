package com.github.jekwan.instagram.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "follow")
public class Follow {

    @EmbeddedId
    private FollowId id;

    @ManyToOne
    @MapsId("followerId")
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @MapsId("followeeId")
    @JoinColumn(name = "followee_id")
    private User followee;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    public Follow() {}
    public Follow(FollowId id) {
        this.id = id;
    }

    public FollowId getId() {
        return id;
    }

    public void setId(FollowId id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
