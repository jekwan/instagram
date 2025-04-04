package com.github.jekwan.instagram.repository;

import com.github.jekwan.instagram.entity.PostMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMediaRepository extends JpaRepository<PostMedia, Long> {
}
