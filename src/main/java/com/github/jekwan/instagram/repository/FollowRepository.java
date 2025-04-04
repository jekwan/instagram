package com.github.jekwan.instagram.repository;

import com.github.jekwan.instagram.entity.Follow;
import com.github.jekwan.instagram.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    List<Follow> findByIdFollowerId(Integer followerId);
    List<Follow> findByIdFolloweeId(Integer followeeId);
}
