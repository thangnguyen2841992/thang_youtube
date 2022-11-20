package com.thang.youtube.repository;

import com.thang.youtube.model.entity.LikedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILikedVideoRepository extends JpaRepository<LikedVideo, Long> {
    List<LikedVideo> findLikedVideoByUser_Id(Long userId);

    Optional<LikedVideo> findLikedVideosByUser_IdAndVideo_Id(Long userId, Long videoId);
}
