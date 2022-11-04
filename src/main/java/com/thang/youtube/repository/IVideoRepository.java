package com.thang.youtube.repository;

import com.thang.youtube.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVideoRepository extends JpaRepository<Video, Long> {
    @Query(value = "select * from videos where user_id <> ?1 order by date_created desc", nativeQuery = true)
    List<Video> getVideoOtherUser(Long userId);
    List<Video> getVideoByUser_Id(Long userId);

    @Query(value = "select url from videos where id = ?1", nativeQuery = true)
    String getUrlById(Long videoId);

    @Query(value = "select * from videos where user_id != ?1 and hastag_id = ?2 order by date_created desc", nativeQuery = true)
    List<Video> findAllVideoByHastag(Long userId, Long hastagId);
    @Query(value = "select * from videos where user_id != ?1  and id != ?2 order by date_created desc", nativeQuery = true)
    List<Video> findAllVideoOtherUserAndOtherCurrentVideo(Long userId, Long videoId);
}
