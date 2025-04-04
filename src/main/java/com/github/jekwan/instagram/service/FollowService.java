package com.github.jekwan.instagram.service;

import com.github.jekwan.instagram.entity.Follow;
import com.github.jekwan.instagram.entity.FollowId;
import com.github.jekwan.instagram.repository.FollowRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Transactional
    public boolean follow(Long followerId, Long followeeId) {
        FollowId followId = new FollowId(followerId, followeeId);
        if (followRepository.existsById(followId)) {
            return false;
        }

        Follow follow = new Follow(followId);
        followRepository.save(follow);
        return true;
    }

    @Transactional
    public boolean unfollow(Long followerId, Long followeeId) {
        FollowId followId = new FollowId(followerId, followeeId);
        if (followRepository.existsById(followId)) {
            followRepository.deleteById(followId);
            return true;
        }

        return false;
    }
}
