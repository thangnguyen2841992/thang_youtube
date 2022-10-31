package com.thang.youtube.repository;

import com.thang.youtube.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVideoRepository extends JpaRepository<Video, Long> {
    List<Video> getVideoByUser_Id(Long userId);

    @Query(value = "select * from videos where user_id != ?1 and hastag_id = ?2 order by date_created desc", nativeQuery = true)
    List<Video> findAllVideoByHastag(Long userId, Long hastagId);
}
