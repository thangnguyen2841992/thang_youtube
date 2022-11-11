package com.thang.youtube.repository;

import com.thang.youtube.model.entity.WatchedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWatchedVideoRepository extends JpaRepository<WatchedVideo, Long> {
    List<WatchedVideo>  findWatchedVideoByUser_IdOrderByWatchedTime(Long userId);
}
