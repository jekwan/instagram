package com.github.jekwan.instagram.repository;

import com.github.jekwan.instagram.entity.Follow;
import com.github.jekwan.instagram.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    List<Follow> findByIdFollowerId(Integer followerId);
    List<Follow> findByIdFolloweeId(Integer followeeId);


    @Query("SELECT f.followee.id from Follow f WHERE f.follower.id = :followerId")
    List<Long> findFolloweeIdsByFollowerId(Long followerId);

}
