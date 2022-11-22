package com.thang.youtube.repository;

import com.thang.youtube.model.entity.LikedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ILikedVideoRepository extends JpaRepository<LikedVideo, Long> {
  @Query(value = "select * from liked_videos where user_id = ?1 order by liked_time desc limit ?2 ", nativeQuery = true)
    List<LikedVideo> findLikedVideoOfUser(Long userId, int limit);

    Optional<LikedVideo> findLikedVideosByUser_IdAndVideo_Id(Long userId, Long videoId);
}
