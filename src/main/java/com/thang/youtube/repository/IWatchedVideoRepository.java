package com.thang.youtube.repository;

import com.thang.youtube.model.entity.WatchedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface IWatchedVideoRepository extends JpaRepository<WatchedVideo, Long> {
   @Query(value = "select * from watched_videos where user_id = ?1 order by watched_time desc limit  ?2 offset  0 ", nativeQuery = true)
    List<WatchedVideo>  findWatchedVideoOfUser(Long userId,  int limit);

    Optional<WatchedVideo> findWatchedVideosByUser_IdAndVideo_Id(Long userId, Long videoId);
}
