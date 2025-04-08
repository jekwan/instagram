package com.github.jekwan.instagram.repository;

import com.github.jekwan.instagram.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id IN :userIds ORDER BY p.createdAt DESC")
    List<Post> findPostsByUserIds(List<Long> userIds);

    @Query("SELECT p FROM Post p JOIN FETCH p.mediaList WHERE p.user.id in :userIds ORDER BY p.createdAt DESC")
    List<Post> findPostsWithMediaByUserIds(List<Long> userIds);

    @Query("SELECT DISTINCT p " +
            "FROM Post p LEFT JOIN FETCH p.mediaList JOIN FETCH p.user " +
            "WHERE p.user.id IN (SELECT f.followee.id FROM Follow f WHERE f.follower.id = :followerId) " +
            "ORDER BY p.createdAt DESC")
    List<Post> findNewsFeedByFollowerId(Long followerId);
}
