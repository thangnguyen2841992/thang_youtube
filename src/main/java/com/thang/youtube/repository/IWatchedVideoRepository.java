package com.thang.youtube.repository;

import com.thang.youtube.model.entity.WatchedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IWatchedVideoRepository extends JpaRepository<WatchedVideo, Long> {
    List<WatchedVideo>  findDistinctVideo_IdByUser_IdOrderByWatchedTime(Long userId);

    Optional<WatchedVideo> findWatchedVideosByUser_IdAndVideo_Id(Long userId, Long videoId);
}
