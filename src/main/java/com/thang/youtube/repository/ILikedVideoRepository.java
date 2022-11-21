package com.thang.youtube.repository;

import com.thang.youtube.model.entity.LikedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikedVideoRepository extends JpaRepository<LikedVideo, Long> {
}
